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

package com.smartstudio.deviceinfo.controllers.about;

import com.smartstudio.deviceinfo.R;
import com.smartstudio.deviceinfo.analytics.about.AboutAnalytics;
import com.smartstudio.deviceinfo.injection.qualifiers.ForFabric;
import com.smartstudio.deviceinfo.injection.qualifiers.ForGoogle;
import com.smartstudio.deviceinfo.injection.scopes.PerActivity;
import com.smartstudio.deviceinfo.ui.BaseView;
import com.smartstudio.deviceinfo.ui.about.AboutView;

import dagger.Module;
import dagger.Provides;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Module
public class AboutActivityTestModule {

    @Provides
    @PerActivity
    AboutView provideView() {
        AboutView view = mock(AboutView.class);
        when(view.getLayoutResourceId()).thenReturn(R.layout.activity_about);
        return view;
    }

    @Provides
    @PerActivity
    BaseView provideBaseView(AboutView view) {
        return view;
    }

    @Provides
    @ForGoogle
    @PerActivity
    AboutAnalytics provideAnalytics() {
        return mock(AboutAnalytics.class);
    }

    @Provides
    @ForFabric
    @PerActivity
    AboutAnalytics provideFabricAnalytics() {
        return mock(AboutAnalytics.class);
    }
}
