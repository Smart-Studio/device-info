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

package com.smartstudio.deviceinfo.controllers.dashboard.screeninfo;

import com.smartstudio.deviceinfo.R;
import com.smartstudio.deviceinfo.analytics.dashboard.screeninfo.ScreenInfoAnalytics;
import com.smartstudio.deviceinfo.injection.qualifiers.ForFabric;
import com.smartstudio.deviceinfo.injection.qualifiers.ForFragment;
import com.smartstudio.deviceinfo.injection.qualifiers.ForGoogle;
import com.smartstudio.deviceinfo.injection.scopes.PerFragment;
import com.smartstudio.deviceinfo.logic.ScreenInfoManager;
import com.smartstudio.deviceinfo.model.ScreenInfo;
import com.smartstudio.deviceinfo.ui.BaseView;
import com.smartstudio.deviceinfo.ui.dashboard.screeninfo.ScreenInfoView;

import dagger.Module;
import dagger.Provides;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Module
public class ScreenInfoFragmentTestModule {

    @Provides
    @PerFragment
    ScreenInfoView provideView() {
        ScreenInfoView view = mock(ScreenInfoView.class);
        when(view.getLayoutResourceId()).thenReturn(R.layout.fragment_screen_info);
        return view;
    }

    @Provides
    @PerFragment
    @ForFragment
    BaseView provideBaseView(ScreenInfoView view) {
        return view;
    }

    @Provides
    @PerFragment
    ScreenInfoManager provideScreenInfoManager(ScreenInfo screenInfo) {
        ScreenInfoManager screenInfoManager = mock(ScreenInfoManager.class);
        when(screenInfoManager.getScreenInfo()).thenReturn(screenInfo);
        return screenInfoManager;
    }

    @Provides
    @PerFragment
    ScreenInfo provideScreenInfo() {
        return mock(ScreenInfo.class);
    }

    @Provides
    @PerFragment
    @ForGoogle
    ScreenInfoAnalytics provideAnalytics() {
        return mock(ScreenInfoAnalytics.class);
    }

    @Provides
    @PerFragment
    @ForFabric
    ScreenInfoAnalytics provideFabricAnalytics() {
        return mock(ScreenInfoAnalytics.class);
    }
}