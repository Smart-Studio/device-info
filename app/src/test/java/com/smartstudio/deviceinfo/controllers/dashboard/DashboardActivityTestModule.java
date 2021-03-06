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

package com.smartstudio.deviceinfo.controllers.dashboard;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.os.Bundle;

import com.crashlytics.android.answers.Answers;
import com.crashlytics.android.answers.ContentViewEvent;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.smartstudio.deviceinfo.R;
import com.smartstudio.deviceinfo.analytics.about.AboutAnalytics;
import com.smartstudio.deviceinfo.analytics.dashboard.DashboardAnalytics;
import com.smartstudio.deviceinfo.injection.qualifiers.ForActivity;
import com.smartstudio.deviceinfo.injection.qualifiers.ForApplication;
import com.smartstudio.deviceinfo.injection.qualifiers.ForFabric;
import com.smartstudio.deviceinfo.injection.qualifiers.ForGoogle;
import com.smartstudio.deviceinfo.injection.scopes.PerActivity;
import com.smartstudio.deviceinfo.ui.BaseView;
import com.smartstudio.deviceinfo.ui.dashboard.DashboardView;

import dagger.Module;
import dagger.Provides;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Module
public class DashboardActivityTestModule {

    @Provides
    @PerActivity
    DashboardView provideView() {
        DashboardView view = mock(DashboardView.class);
        when(view.getLayoutResourceId()).thenReturn(R.layout.activity_dashboard);
        return view;
    }

    @Provides
    @PerActivity
    @ForActivity
    BaseView provideBaseView(DashboardView view) {
        return view;
    }

    @Provides
    @PerActivity
    @ForGoogle
    DashboardAnalytics provideDashboardAnalytics() {
        return mock(DashboardAnalytics.class);
    }

    @Provides
    @PerActivity
    @ForFabric
    DashboardAnalytics provideDashboardFabricAnalytics() {
        return mock(DashboardAnalytics.class);
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

    @Provides
    ContentViewEvent provideContentViewEvent() {
        return new ContentViewEvent();
    }

    @Provides
    Answers provideAnswers() {
        return new Answers();
    }

    @Provides
    @ForActivity
    Context provideContext() {
        return mock(Context.class);
    }

    @Provides
    @ForApplication
    Context provideAppContext() {
        return mock(Context.class);
    }


    @Provides
    Intent provideIntent() {
        return mock(Intent.class);
    }

    @Provides
    Resources provideResources() {
        return mock(Resources.class);
    }

    @Provides
    IntentFilter provideIntentFilter() {
        return mock(IntentFilter.class);
    }

    @Provides
    Bundle provideBundle() {
        return mock(Bundle.class);
    }

    @Provides
    FirebaseAnalytics provideFirebaseAnalytics() {
        return mock(FirebaseAnalytics.class);
    }
}
