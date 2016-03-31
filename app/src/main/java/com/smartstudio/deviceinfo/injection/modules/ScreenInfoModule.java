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

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;

import com.smartstudio.deviceinfo.analytics.screeninfo.ScreenInfoAnalytics;
import com.smartstudio.deviceinfo.analytics.screeninfo.ScreenInfoAnalyticsImpl;
import com.smartstudio.deviceinfo.analytics.screeninfo.ScreenInfoFabricAnalytics;
import com.smartstudio.deviceinfo.controllers.screeninfo.ScreenInfoController;
import com.smartstudio.deviceinfo.injection.qualifiers.ForActivity;
import com.smartstudio.deviceinfo.injection.qualifiers.ForFabric;
import com.smartstudio.deviceinfo.injection.qualifiers.ForGoogle;
import com.smartstudio.deviceinfo.injection.scopes.PerActivity;
import com.smartstudio.deviceinfo.logic.ScreenInfoManager;
import com.smartstudio.deviceinfo.logic.ScreenInfoManagerImpl;
import com.smartstudio.deviceinfo.model.ScreenInfo;
import com.smartstudio.deviceinfo.ui.BaseView;
import com.smartstudio.deviceinfo.ui.screeninfo.ScreenInfoView;
import com.smartstudio.deviceinfo.ui.screeninfo.ScreenInfoViewImpl;

import dagger.Module;
import dagger.Provides;

@Module
public class ScreenInfoModule {
    private final ScreenInfoController mController;
    private final Context mContext;
    private final Display mDisplay;

    public ScreenInfoModule(ScreenInfoController controller, Context context, Display display) {
        mController = controller;
        mContext = context;
        mDisplay = display;
    }

    @Provides
    @PerActivity
    ScreenInfoController provideController() {
        return mController;
    }

    @Provides
    @ForActivity
    Context provideContext() {
        return mContext;
    }

    @Provides
    @PerActivity
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
    @PerActivity
    ScreenInfoManager provideScreenInfoManager(ScreenInfoManagerImpl screenInfoManager) {
        return screenInfoManager;
    }

    @Provides
    @PerActivity
    ScreenInfoView provideView(ScreenInfoViewImpl view) {
        return view;
    }

    @Provides
    @PerActivity
    BaseView provideBaseView(ScreenInfoView view) {
        return view;
    }

    @Provides
    @PerActivity
    @ForGoogle
    ScreenInfoAnalytics provideAnalytics(ScreenInfoAnalyticsImpl analytics) {
        return analytics;
    }

    @Provides
    @PerActivity
    @ForFabric
    ScreenInfoAnalytics provideFabricAnalytics(ScreenInfoFabricAnalytics analytics) {
        return analytics;
    }

    @Provides
    TypedValue provideTypedValue() {
        return new TypedValue();
    }
}
