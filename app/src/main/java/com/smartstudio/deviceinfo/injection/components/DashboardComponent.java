/*
 * Copyright 2016 Smart Studio.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.smartstudio.deviceinfo.injection.components;

import com.smartstudio.deviceinfo.controllers.dashboard.DashboardActivity;
import com.smartstudio.deviceinfo.injection.modules.BatteryModule;
import com.smartstudio.deviceinfo.injection.modules.DashboardModule;
import com.smartstudio.deviceinfo.injection.modules.ScreenInfoModule;
import com.smartstudio.deviceinfo.injection.modules.SystemModule;
import com.smartstudio.deviceinfo.injection.scopes.PerActivity;

import dagger.Subcomponent;

@PerActivity
@Subcomponent(modules = DashboardModule.class)
public interface DashboardComponent {
    void inject(DashboardActivity activity);

    ScreenInfoComponent plus(ScreenInfoModule module);

    SystemComponent plus(SystemModule module);

    BatteryComponent plus(BatteryModule module);
}
