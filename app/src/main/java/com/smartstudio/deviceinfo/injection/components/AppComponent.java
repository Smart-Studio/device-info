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

import com.smartstudio.deviceinfo.DeviceInfoApp;
import com.smartstudio.deviceinfo.injection.modules.AboutModule;
import com.smartstudio.deviceinfo.injection.modules.AnalyticsModule;
import com.smartstudio.deviceinfo.injection.modules.AppModule;
import com.smartstudio.deviceinfo.injection.modules.AttributionsModule;
import com.smartstudio.deviceinfo.injection.modules.DashboardModule;
import com.smartstudio.deviceinfo.injection.modules.ScreenInfoModule;
import com.smartstudio.deviceinfo.injection.scopes.PerApplication;

import dagger.Component;

@PerApplication
@Component(modules = {AppModule.class, AnalyticsModule.class})
public interface AppComponent {
    void inject(DeviceInfoApp app);

    DashboardComponent plus(DashboardModule module);

    AboutComponent plus(AboutModule module);

    AttributionsComponent plus(AttributionsModule module);
}
