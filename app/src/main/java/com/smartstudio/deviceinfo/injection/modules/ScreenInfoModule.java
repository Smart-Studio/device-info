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

package com.smartstudio.deviceinfo.injection.modules;

import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;

import com.smartstudio.deviceinfo.analytics.dashboard.screeninfo.ScreenInfoAnalytics;
import com.smartstudio.deviceinfo.analytics.dashboard.screeninfo.ScreenInfoAnalyticsImpl;
import com.smartstudio.deviceinfo.analytics.dashboard.screeninfo.ScreenInfoFabricAnalytics;
import com.smartstudio.deviceinfo.controllers.dashboard.screeninfo.ScreenInfoController;
import com.smartstudio.deviceinfo.injection.qualifiers.ForFabric;
import com.smartstudio.deviceinfo.injection.qualifiers.ForFragment;
import com.smartstudio.deviceinfo.injection.qualifiers.ForGoogle;
import com.smartstudio.deviceinfo.injection.scopes.PerFragment;
import com.smartstudio.deviceinfo.logic.dashboard.screeninfo.ScreenInfoManagerImpl;
import com.smartstudio.deviceinfo.logic.dashboard.screeninfo.ScreenInfoManager;
import com.smartstudio.deviceinfo.logic.dashboard.screeninfo.ScreenInfoShareManager;
import com.smartstudio.deviceinfo.logic.dashboard.screeninfo.ScreenInfoShareManagerImpl;
import com.smartstudio.deviceinfo.model.ScreenInfo;
import com.smartstudio.deviceinfo.model.ScreenInfoViewModel;
import com.smartstudio.deviceinfo.ui.BaseView;
import com.smartstudio.deviceinfo.ui.dashboard.screeninfo.ScreenInfoView;
import com.smartstudio.deviceinfo.ui.dashboard.screeninfo.ScreenInfoViewImpl;

import dagger.Module;
import dagger.Provides;

@Module
public class ScreenInfoModule {
    private final ScreenInfoController mController;
    private final Display mDisplay;

    public ScreenInfoModule(ScreenInfoController controller, Display display) {
        mController = controller;
        mDisplay = display;
    }

    @Provides
    @PerFragment
    ScreenInfoController provideController() {
        return mController;
    }

    @Provides
    @PerFragment
    Display provideDisplay() {
        return mDisplay;
    }

    @Provides
    DisplayMetrics provideDisplayMetrics() {
        return new DisplayMetrics();
    }

    @Provides
    ScreenInfo provideScreenInfo() {
        return new ScreenInfo();
    }

    @Provides
    @PerFragment
    ScreenInfoManager provideScreenInfoManager(ScreenInfoManagerImpl screenInfoManager) {
        return screenInfoManager;
    }

    @Provides
    @PerFragment
    ScreenInfoView provideView(ScreenInfoViewImpl view) {
        return view;
    }

    @Provides
    @PerFragment
    @ForFragment
    BaseView provideBaseView(ScreenInfoView view) {
        return view;
    }

    @Provides
    TypedValue provideTypedValue() {
        return new TypedValue();
    }

    @Provides
    @PerFragment
    @ForGoogle
    ScreenInfoAnalytics provideAnalytics(ScreenInfoAnalyticsImpl analytics) {
        return analytics;
    }

    @Provides
    @PerFragment
    @ForFabric
    ScreenInfoAnalytics provideFabricAnalytics(ScreenInfoFabricAnalytics analytics) {
        return analytics;
    }

    @Provides
    @PerFragment
    ScreenInfoShareManager provideScreenInfoSharer(ScreenInfoShareManagerImpl shareManager) {
        return shareManager;
    }

    @Provides
    ScreenInfoViewModel provideScreenInfoViewModel() {
        return new ScreenInfoViewModel();
    }
}
