/*
 * Copyright (C) 2017 Moez Bhatti <moez.bhatti@gmail.com>
 *
 * This file is part of QKSMS.
 *
 * QKSMS is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * QKSMS is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with QKSMS.  If not, see <http://www.gnu.org/licenses/>.
 */
package de.asdfghasdfgh.kotozute.injection.android

import dagger.Module
import dagger.android.ContributesAndroidInjector
import de.asdfghasdfgh.kotozute.feature.backup.BackupActivity
import de.asdfghasdfgh.kotozute.feature.blocking.BlockingActivity
import de.asdfghasdfgh.kotozute.feature.signal.SignalConversationsActivity
import de.asdfghasdfgh.kotozute.feature.signal.SignalGifActivity
import de.asdfghasdfgh.kotozute.feature.signal.SignalNewGroupActivity
import de.asdfghasdfgh.kotozute.feature.signal.SignalLinkActivity
import de.asdfghasdfgh.kotozute.feature.signal.SignalRegisterActivity
import de.asdfghasdfgh.kotozute.feature.signal.SignalThreadActivity
import de.asdfghasdfgh.kotozute.feature.signal.SignalThreadInfoActivity
import de.asdfghasdfgh.kotozute.feature.signal.ViewOnceActivity
import de.asdfghasdfgh.kotozute.feature.compose.ComposeActivity
import de.asdfghasdfgh.kotozute.feature.compose.ComposeActivityModule
import de.asdfghasdfgh.kotozute.feature.contacts.ContactsActivity
import de.asdfghasdfgh.kotozute.feature.contacts.ContactsActivityModule
import de.asdfghasdfgh.kotozute.feature.conversationinfo.ConversationInfoActivity
import de.asdfghasdfgh.kotozute.feature.gallery.GalleryActivity
import de.asdfghasdfgh.kotozute.feature.gallery.GalleryActivityModule
import de.asdfghasdfgh.kotozute.feature.main.MainActivity
import de.asdfghasdfgh.kotozute.feature.main.MainActivityModule
import de.asdfghasdfgh.kotozute.feature.notificationprefs.NotificationPrefsActivity
import de.asdfghasdfgh.kotozute.feature.notificationprefs.NotificationPrefsActivityModule
import de.asdfghasdfgh.kotozute.feature.qkreply.QkReplyActivity
import de.asdfghasdfgh.kotozute.feature.qkreply.QkReplyActivityModule
import de.asdfghasdfgh.kotozute.feature.scheduled.ScheduledActivity
import de.asdfghasdfgh.kotozute.feature.scheduled.ScheduledActivityModule
import de.asdfghasdfgh.kotozute.feature.settings.SettingsActivity
import de.asdfghasdfgh.kotozute.injection.scope.ActivityScope

@Module
abstract class ActivityBuilderModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun bindMainActivity(): MainActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [])
    abstract fun bindBackupActivity(): BackupActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [ComposeActivityModule::class])
    abstract fun bindComposeActivity(): ComposeActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [ContactsActivityModule::class])
    abstract fun bindContactsActivity(): ContactsActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [])
    abstract fun bindConversationInfoActivity(): ConversationInfoActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [GalleryActivityModule::class])
    abstract fun bindGalleryActivity(): GalleryActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [NotificationPrefsActivityModule::class])
    abstract fun bindNotificationPrefsActivity(): NotificationPrefsActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [QkReplyActivityModule::class])
    abstract fun bindQkReplyActivity(): QkReplyActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [ScheduledActivityModule::class])
    abstract fun bindScheduledActivity(): ScheduledActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [])
    abstract fun bindSettingsActivity(): SettingsActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [])
    abstract fun bindBlockingActivity(): BlockingActivity


    @ActivityScope
    @ContributesAndroidInjector(modules = [])
    abstract fun bindSignalConversationsActivity(): SignalConversationsActivity

    @ContributesAndroidInjector
    abstract fun bindSignalNewGroupActivity(): SignalNewGroupActivity

    @ContributesAndroidInjector
    abstract fun bindSignalGifActivity(): SignalGifActivity

    @ContributesAndroidInjector
    abstract fun bindSignalLinkActivity(): SignalLinkActivity

    @ContributesAndroidInjector
    abstract fun bindSetupActivity(): de.asdfghasdfgh.kotozute.feature.setup.SetupActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [])
    abstract fun bindSignalCallActivity(): de.asdfghasdfgh.kotozute.feature.signalcall.SignalCallActivity

    @ContributesAndroidInjector
    abstract fun bindSignalRegisterActivity(): SignalRegisterActivity


    @ActivityScope
    @ContributesAndroidInjector(modules = [])
    abstract fun bindSignalThreadActivity(): SignalThreadActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [])
    abstract fun bindSignalThreadInfoActivity(): SignalThreadInfoActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [])
    abstract fun bindViewOnceActivity(): ViewOnceActivity

}
