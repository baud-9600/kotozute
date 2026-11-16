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
package de.asdfghasdfgh.kotozute.injection

import android.app.Application
import android.content.ContentResolver
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.lifecycle.ViewModelProvider
import androidx.work.WorkerFactory
import com.f2prateek.rx.preferences2.RxSharedPreferences
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import de.asdfghasdfgh.kotozute.blocking.BlockingClient
import de.asdfghasdfgh.kotozute.blocking.BlockingManager
import de.asdfghasdfgh.kotozute.common.ViewModelFactory
import de.asdfghasdfgh.kotozute.common.util.NotificationManagerImpl
import de.asdfghasdfgh.kotozute.common.util.ShortcutManagerImpl
import de.asdfghasdfgh.kotozute.feature.conversationinfo.injection.ConversationInfoComponent
import de.asdfghasdfgh.kotozute.listener.ContactAddedListener
import de.asdfghasdfgh.kotozute.listener.ContactAddedListenerImpl
import de.asdfghasdfgh.kotozute.manager.ActiveConversationManager
import de.asdfghasdfgh.kotozute.manager.ActiveConversationManagerImpl
import de.asdfghasdfgh.kotozute.manager.AlarmManager
import de.asdfghasdfgh.kotozute.manager.AlarmManagerImpl
import de.asdfghasdfgh.kotozute.manager.KeyManager
import de.asdfghasdfgh.kotozute.manager.KeyManagerImpl
import de.asdfghasdfgh.kotozute.manager.NotificationManager
import de.asdfghasdfgh.kotozute.manager.PermissionManager
import de.asdfghasdfgh.kotozute.manager.PermissionManagerImpl
import de.asdfghasdfgh.kotozute.manager.ReferralManager
import de.asdfghasdfgh.kotozute.manager.ReferralManagerImpl
import de.asdfghasdfgh.kotozute.manager.ShortcutManager
import de.asdfghasdfgh.kotozute.manager.WidgetManager
import de.asdfghasdfgh.kotozute.manager.WidgetManagerImpl
import de.asdfghasdfgh.kotozute.mapper.CursorToContact
import de.asdfghasdfgh.kotozute.mapper.CursorToContactGroup
import de.asdfghasdfgh.kotozute.mapper.CursorToContactGroupImpl
import de.asdfghasdfgh.kotozute.mapper.CursorToContactGroupMember
import de.asdfghasdfgh.kotozute.mapper.CursorToContactGroupMemberImpl
import de.asdfghasdfgh.kotozute.mapper.CursorToContactImpl
import de.asdfghasdfgh.kotozute.mapper.CursorToConversation
import de.asdfghasdfgh.kotozute.mapper.CursorToConversationImpl
import de.asdfghasdfgh.kotozute.mapper.CursorToMessage
import de.asdfghasdfgh.kotozute.mapper.CursorToMessageImpl
import de.asdfghasdfgh.kotozute.mapper.CursorToPart
import de.asdfghasdfgh.kotozute.mapper.CursorToPartImpl
import de.asdfghasdfgh.kotozute.mapper.CursorToRecipient
import de.asdfghasdfgh.kotozute.mapper.CursorToRecipientImpl
import de.asdfghasdfgh.kotozute.repository.BackupRepository
import de.asdfghasdfgh.kotozute.repository.BackupRepositoryImpl
import de.asdfghasdfgh.kotozute.repository.BlockingRepository
import de.asdfghasdfgh.kotozute.repository.BlockingRepositoryImpl
import de.asdfghasdfgh.kotozute.repository.ContactRepository
import de.asdfghasdfgh.kotozute.repository.ContactRepositoryImpl
import de.asdfghasdfgh.kotozute.repository.ConversationRepository
import de.asdfghasdfgh.kotozute.repository.ConversationRepositoryImpl
import de.asdfghasdfgh.kotozute.repository.EmojiReactionRepository
import de.asdfghasdfgh.kotozute.repository.EmojiReactionRepositoryImpl
import de.asdfghasdfgh.kotozute.repository.MessageContentFilterRepository
import de.asdfghasdfgh.kotozute.repository.MessageContentFilterRepositoryImpl
import de.asdfghasdfgh.kotozute.repository.MessageRepository
import de.asdfghasdfgh.kotozute.repository.MessageRepositoryImpl
import de.asdfghasdfgh.kotozute.repository.ScheduledMessageRepository
import de.asdfghasdfgh.kotozute.repository.ScheduledMessageRepositoryImpl
import de.asdfghasdfgh.kotozute.repository.SignalRepository
import de.asdfghasdfgh.kotozute.repository.SignalRepositoryImpl
import de.asdfghasdfgh.kotozute.repository.SyncRepository
import de.asdfghasdfgh.kotozute.repository.SyncRepositoryImpl
import de.asdfghasdfgh.kotozute.worker.InjectionWorkerFactory
import javax.inject.Singleton

@Module(subcomponents = [
    ConversationInfoComponent::class])
class AppModule(private var application: Application) {

    @Provides
    @Singleton
    fun provideContext(): Context = application

    @Provides
    fun provideContentResolver(context: Context): ContentResolver = context.contentResolver

    @Provides
    @Singleton
    fun provideSharedPreferences(context: Context): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }

    @Provides
    @Singleton
    fun provideRxPreferences(preferences: SharedPreferences): RxSharedPreferences {
        return RxSharedPreferences.create(preferences)
    }

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()
    }

    @Provides
    fun provideViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory = factory

    // Listener

    @Provides
    fun provideContactAddedListener(listener: ContactAddedListenerImpl): ContactAddedListener = listener

    // Manager

    @Provides
    fun provideActiveConversationManager(manager: ActiveConversationManagerImpl): ActiveConversationManager = manager

    @Provides
    fun provideAlarmManager(manager: AlarmManagerImpl): AlarmManager = manager

    @Provides
    fun blockingClient(manager: BlockingManager): BlockingClient = manager

    @Provides
    fun provideKeyManager(manager: KeyManagerImpl): KeyManager = manager

    @Provides
    fun provideNotificationsManager(manager: NotificationManagerImpl): NotificationManager = manager

    @Provides
    fun providePermissionsManager(manager: PermissionManagerImpl): PermissionManager = manager

    @Provides
    fun provideShortcutManager(manager: ShortcutManagerImpl): ShortcutManager = manager

    @Provides
    fun provideReferralManager(manager: ReferralManagerImpl): ReferralManager = manager

    @Provides
    fun provideWidgetManager(manager: WidgetManagerImpl): WidgetManager = manager

    // Mapper

    @Provides
    fun provideCursorToContact(mapper: CursorToContactImpl): CursorToContact = mapper

    @Provides
    fun provideCursorToContactGroup(mapper: CursorToContactGroupImpl): CursorToContactGroup = mapper

    @Provides
    fun provideCursorToContactGroupMember(mapper: CursorToContactGroupMemberImpl): CursorToContactGroupMember = mapper

    @Provides
    fun provideCursorToConversation(mapper: CursorToConversationImpl): CursorToConversation = mapper

    @Provides
    fun provideCursorToMessage(mapper: CursorToMessageImpl): CursorToMessage = mapper

    @Provides
    fun provideCursorToPart(mapper: CursorToPartImpl): CursorToPart = mapper

    @Provides
    fun provideCursorToRecipient(mapper: CursorToRecipientImpl): CursorToRecipient = mapper

    // Repository

    @Provides
    fun provideBackupRepository(repository: BackupRepositoryImpl): BackupRepository = repository

    @Provides
    fun provideBlockingRepository(repository: BlockingRepositoryImpl): BlockingRepository = repository

    @Provides
    fun provideMessageContentFilterRepository(repository: MessageContentFilterRepositoryImpl): MessageContentFilterRepository = repository

    @Provides
    fun provideContactRepository(repository: ContactRepositoryImpl): ContactRepository = repository

    @Provides
    fun provideConversationRepository(repository: ConversationRepositoryImpl): ConversationRepository = repository

    @Provides
    fun provideMessageRepository(repository: MessageRepositoryImpl): MessageRepository = repository


    /**
     * The only repository here that is a singleton, and it has to be.
     *
     * The others are stateless -- a second instance costs an allocation and behaves
     * identically. This one owns a websocket, the flags that say whether a stream is running,
     * and the BehaviorSubject every Signal screen observes. Handing out separate instances
     * meant separate everything: two listen loops on a device Signal only lets hold **one**
     * authenticated socket, each displacing the other, which showed up as a reconnect every
     * sixty seconds with healthy keepalives on both sides of it.
     *
     * It was invisible on the bridge rail, where a second server-sent-events connection is
     * simply a second connection and the server does not mind. Only the direct link, with one
     * socket per device, made it fail.
     *
     * The state subject is the other half: publishing on one instance never reached a screen
     * holding another.
     */
    @Provides
    @Singleton
    fun provideSignalRepository(repository: SignalRepositoryImpl): SignalRepository = repository

    @Provides
    fun provideScheduledMessagesRepository(repository: ScheduledMessageRepositoryImpl): ScheduledMessageRepository = repository

    @Provides
    fun provideSyncRepository(repository: SyncRepositoryImpl): SyncRepository = repository

    @Provides
    fun provideEmojiReactionRepository(repository: EmojiReactionRepositoryImpl): EmojiReactionRepository = repository

    // worker factory
    @Provides
    fun provideWorkerFactory(workerFactory: InjectionWorkerFactory): WorkerFactory = workerFactory
}