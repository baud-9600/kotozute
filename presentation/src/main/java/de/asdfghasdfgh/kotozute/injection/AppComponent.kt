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

import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import de.asdfghasdfgh.kotozute.common.QKApplication
import de.asdfghasdfgh.kotozute.common.QkDialog
import de.asdfghasdfgh.kotozute.common.util.QkChooserTargetService
import de.asdfghasdfgh.kotozute.common.widget.AvatarView
import de.asdfghasdfgh.kotozute.common.widget.PagerTitleView
import de.asdfghasdfgh.kotozute.common.widget.PreferenceView
import de.asdfghasdfgh.kotozute.common.widget.QkEditText
import de.asdfghasdfgh.kotozute.common.widget.QkSwitch
import de.asdfghasdfgh.kotozute.common.widget.QkTextView
import de.asdfghasdfgh.kotozute.common.widget.RadioPreferenceView
import de.asdfghasdfgh.kotozute.feature.backup.BackupController
import de.asdfghasdfgh.kotozute.feature.blocking.BlockingController
import de.asdfghasdfgh.kotozute.feature.blocking.filters.MessageContentFiltersController
import de.asdfghasdfgh.kotozute.feature.blocking.manager.BlockingManagerController
import de.asdfghasdfgh.kotozute.feature.blocking.messages.BlockedMessagesController
import de.asdfghasdfgh.kotozute.feature.blocking.numbers.BlockedNumbersController
import de.asdfghasdfgh.kotozute.feature.compose.editing.DetailedChipView
import de.asdfghasdfgh.kotozute.feature.conversationinfo.injection.ConversationInfoComponent
import de.asdfghasdfgh.kotozute.feature.settings.SettingsController
import de.asdfghasdfgh.kotozute.feature.settings.swipe.SwipeActionsController
import de.asdfghasdfgh.kotozute.feature.widget.WidgetAdapter
import de.asdfghasdfgh.kotozute.injection.android.ActivityBuilderModule
import de.asdfghasdfgh.kotozute.injection.android.BroadcastReceiverBuilderModule
import de.asdfghasdfgh.kotozute.injection.android.ServiceBuilderModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    AppModule::class,
    ActivityBuilderModule::class,
    BroadcastReceiverBuilderModule::class,
    ServiceBuilderModule::class])
interface AppComponent {

    fun conversationInfoBuilder(): ConversationInfoComponent.Builder

    fun inject(application: QKApplication)

    fun inject(controller: BackupController)
    fun inject(controller: BlockedMessagesController)
    fun inject(controller: BlockedNumbersController)
    fun inject(controller: MessageContentFiltersController)
    fun inject(controller: BlockingController)
    fun inject(controller: BlockingManagerController)
    fun inject(controller: SettingsController)
    fun inject(controller: SwipeActionsController)

    fun inject(dialog: QkDialog)

    fun inject(service: WidgetAdapter)

    /**
     * This can't use AndroidInjection, or else it will crash on pre-marshmallow devices
     */
    fun inject(service: QkChooserTargetService)

    fun inject(view: AvatarView)
    fun inject(view: DetailedChipView)
    fun inject(view: PagerTitleView)
    fun inject(view: PreferenceView)
    fun inject(view: RadioPreferenceView)
    fun inject(view: QkEditText)
    fun inject(view: QkSwitch)
    fun inject(view: QkTextView)

}
