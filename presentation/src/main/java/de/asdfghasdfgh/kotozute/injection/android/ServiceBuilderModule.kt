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
import de.asdfghasdfgh.kotozute.feature.backup.RestoreBackupService
import de.asdfghasdfgh.kotozute.feature.desktopsync.DesktopSyncService
import de.asdfghasdfgh.kotozute.feature.signal.SignalStreamService
import de.asdfghasdfgh.kotozute.injection.scope.ActivityScope
import de.asdfghasdfgh.kotozute.service.HeadlessSmsSendService
import de.asdfghasdfgh.kotozute.service.AutoDeleteService

@Module
abstract class ServiceBuilderModule {

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun bindAutoDeleteService(): AutoDeleteService

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun bindHeadlessSmsSendService(): HeadlessSmsSendService

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun bindRestoreBackupService(): RestoreBackupService

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun bindDesktopSyncService(): DesktopSyncService

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun bindSignalStreamService(): SignalStreamService

    @ActivityScope
    @ContributesAndroidInjector()
    abstract fun bindSignalCallService(): de.asdfghasdfgh.kotozute.feature.signalcall.SignalCallService

}
