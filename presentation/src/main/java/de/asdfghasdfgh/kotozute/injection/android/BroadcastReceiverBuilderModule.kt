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
import de.asdfghasdfgh.kotozute.feature.widget.WidgetProvider
import de.asdfghasdfgh.kotozute.injection.scope.ActivityScope
import de.asdfghasdfgh.kotozute.receiver.BlockThreadReceiver
import de.asdfghasdfgh.kotozute.feature.main.DuraSpeedAllowedReceiver
import de.asdfghasdfgh.kotozute.receiver.BootReceiver
import de.asdfghasdfgh.kotozute.receiver.DefaultSmsChangedReceiver
import de.asdfghasdfgh.kotozute.receiver.DeleteMessagesReceiver
import de.asdfghasdfgh.kotozute.receiver.MmsReceivedReceiver
import de.asdfghasdfgh.kotozute.receiver.MmsWapPushReceiver
import de.asdfghasdfgh.kotozute.receiver.NightModeReceiver
import de.asdfghasdfgh.kotozute.receiver.RemoteMessagingReceiver
import de.asdfghasdfgh.kotozute.receiver.SendScheduledMessageReceiver
import de.asdfghasdfgh.kotozute.receiver.MessageDeliveredReceiver
import de.asdfghasdfgh.kotozute.receiver.SmsProviderChangedReceiver
import de.asdfghasdfgh.kotozute.receiver.SmsReceivedReceiver
import de.asdfghasdfgh.kotozute.receiver.MessageMarkReceiver
import de.asdfghasdfgh.kotozute.receiver.MessageSentReceiver
import de.asdfghasdfgh.kotozute.receiver.ResendMessageReceiver
import de.asdfghasdfgh.kotozute.receiver.SendDelayedMessageReceiver
import de.asdfghasdfgh.kotozute.receiver.SpeakThreadsReceiver
import de.asdfghasdfgh.kotozute.receiver.StartActivityFromWidgetReceiver

@Module
abstract class BroadcastReceiverBuilderModule {

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun bindBlockThreadReceiver(): BlockThreadReceiver

    @ContributesAndroidInjector
    abstract fun bindDuraSpeedAllowedReceiver(): DuraSpeedAllowedReceiver

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun bindBootReceiver(): BootReceiver

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun bindSignalRestoreNotificationsReceiver(): de.asdfghasdfgh.kotozute.feature.signal.SignalRestoreNotificationsReceiver

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun bindSignalNotificationActionReceiver(): de.asdfghasdfgh.kotozute.feature.signal.SignalNotificationActionReceiver

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun bindDefaultSmsChangedReceiver(): DefaultSmsChangedReceiver

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun bindDeleteMessagesReceiver(): DeleteMessagesReceiver

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun bindSpeakThreadsReceiver(): SpeakThreadsReceiver

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun bindStartActivityFromWidgetReceiver(): StartActivityFromWidgetReceiver

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun bindMmsReceivedReceiver(): MmsReceivedReceiver

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun bindMmsWapPushReceiver(): MmsWapPushReceiver

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun bindNightModeReceiver(): NightModeReceiver

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun bindRemoteMessagingReceiver(): RemoteMessagingReceiver

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun bindResendMessageReceiver(): ResendMessageReceiver

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun bindSendScheduledMessageReceiver(): SendScheduledMessageReceiver

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun bindSendDelayedMessageReceiver(): SendDelayedMessageReceiver

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun bindMessageDeliveredReceiver(): MessageDeliveredReceiver

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun bindSmsProviderChangedReceiver(): SmsProviderChangedReceiver

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun bindSmsReceivedReceiver(): SmsReceivedReceiver

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun bindMessageSentReceiver(): MessageSentReceiver

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun bindMessageMarkReceiver(): MessageMarkReceiver

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun bindWidgetProvider(): WidgetProvider

}