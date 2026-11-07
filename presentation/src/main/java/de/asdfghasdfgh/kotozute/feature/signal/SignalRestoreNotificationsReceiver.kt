package de.asdfghasdfgh.kotozute.feature.signal

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import dagger.android.AndroidInjection
import javax.inject.Inject
import kotlin.concurrent.thread

/**
 * After a restart or an update, puts back the Signal notifications the system dropped.
 * See [SignalNotifications.restore]; upstream's `RestoreNotificationsReceiver`.
 */
class SignalRestoreNotificationsReceiver : BroadcastReceiver() {

    @Inject lateinit var notifications: SignalNotifications
    @Inject lateinit var prefs: de.asdfghasdfgh.kotozute.util.Preferences
    @Inject lateinit var conversationRepo: de.asdfghasdfgh.kotozute.repository.ConversationRepository
    @Inject lateinit var notificationManager: de.asdfghasdfgh.kotozute.manager.NotificationManager

    override fun onReceive(context: Context, intent: Intent?) {
        AndroidInjection.inject(this, context)
        // And catch up straight away after a restart, as upstream's `BootReceiver` does,
        // rather than at the next periodic run.
        if (intent?.action == Intent.ACTION_BOOT_COMPLETED && prefs.signalEnabled.get()) {
            de.asdfghasdfgh.kotozute.worker.SignalSyncWorker.now(context)
        }
        val result = goAsync()
        // Realm and the notification manager both, off the main thread.
        thread(isDaemon = true) {
            try {
                notifications.restore()
                // The text rail loses its notifications the same way. `update` shows only what
                // is unread and not yet seen, and swiping a text notification away already marks
                // it seen, so what somebody dismissed stays dismissed here too.
                runCatching {
                    conversationRepo.getUnreadIds().forEach { notificationManager.update(it, silent = true) }
                }.onFailure { timber.log.Timber.w(it, "notify: could not put the text notifications back") }
            } finally {
                result.finish()
            }
        }
    }
}
