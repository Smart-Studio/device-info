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

package com.smartstudio.deviceinfo.injection;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.view.Display;

import com.smartstudio.deviceinfo.controllers.about.AboutController;
import com.smartstudio.deviceinfo.controllers.about.attributions.AttributionsController;
import com.smartstudio.deviceinfo.controllers.dashboard.DashboardController;
import com.smartstudio.deviceinfo.controllers.dashboard.screeninfo.ScreenInfoController;
import com.smartstudio.deviceinfo.injection.modules.AboutModule;
import com.smartstudio.deviceinfo.injection.modules.AppModule;
import com.smartstudio.deviceinfo.injection.modules.AttributionsModule;
import com.smartstudio.deviceinfo.injection.modules.DashboardModule;
import com.smartstudio.deviceinfo.injection.modules.ScreenInfoModule;

public final class Injector {
    public static AppModule provideAppModule(Application app) {
        return new AppModule(app);
    }

    public static ScreenInfoModule provideScreenInfoModule(ScreenInfoController controller,
                                                           Display display) {
        return new ScreenInfoModule(controller, display);
    }

    public static AboutModule provideAboutModule(AboutController controller, Context context) {
        return new AboutModule(controller, context);
    }

    public static AttributionsModule provideAttributionsModule(AttributionsController controller, Context context) {
        return new AttributionsModule(controller, context);
    }

    public static DashboardModule provideDashboardModule(DashboardController controller, Activity activity, FragmentManager fragmentManager) {
        return new DashboardModule(controller, activity, fragmentManager);
    }

    private Injector() {

    }
}
