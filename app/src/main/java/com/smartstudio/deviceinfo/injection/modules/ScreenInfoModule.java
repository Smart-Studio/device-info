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
import android.view.Display;

import com.smartstudio.deviceinfo.controllers.screeninfo.ScreenInfoController;
import com.smartstudio.deviceinfo.injection.scopes.PerActivity;
import com.smartstudio.deviceinfo.logic.ScreenInfoManager;
import com.smartstudio.deviceinfo.logic.ScreenInfoManagerImpl;
import com.smartstudio.deviceinfo.model.ScreenInfo;
import com.smartstudio.deviceinfo.ui.screeninfo.ScreenInfoView;
import com.smartstudio.deviceinfo.ui.screeninfo.ScreenInfoViewImpl;

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
    @PerActivity
    ScreenInfoController provideController() {
        return mController;
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
}
