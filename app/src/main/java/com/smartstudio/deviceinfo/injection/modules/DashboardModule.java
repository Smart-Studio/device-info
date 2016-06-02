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

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentManager;

import com.smartstudio.deviceinfo.analytics.dashboard.DashboardAnalytics;
import com.smartstudio.deviceinfo.analytics.dashboard.DashboardAnalyticsImpl;
import com.smartstudio.deviceinfo.analytics.dashboard.DashboardFabricAnalytics;
import com.smartstudio.deviceinfo.controllers.dashboard.DashboardController;
import com.smartstudio.deviceinfo.injection.qualifiers.ForActivity;
import com.smartstudio.deviceinfo.injection.qualifiers.ForFabric;
import com.smartstudio.deviceinfo.injection.qualifiers.ForGoogle;
import com.smartstudio.deviceinfo.injection.scopes.PerActivity;
import com.smartstudio.deviceinfo.ui.BaseView;
import com.smartstudio.deviceinfo.ui.dashboard.DashboardPagerAdapter;
import com.smartstudio.deviceinfo.ui.dashboard.DashboardPagerAdapterImpl;
import com.smartstudio.deviceinfo.ui.dashboard.DashboardView;
import com.smartstudio.deviceinfo.ui.dashboard.DashboardViewImpl;

import dagger.Module;
import dagger.Provides;

@Module
public class DashboardModule {
    private final DashboardController mController;
    private final Activity mActivity;
    private final FragmentManager mFragmentManager;

    public DashboardModule(DashboardController controller, Activity activity, FragmentManager fragmentManager) {
        mController = controller;
        mActivity = activity;
        mFragmentManager = fragmentManager;
    }

    @Provides
    @ForActivity
    Context provideContext() {
        return mActivity;
    }

    @Provides
    @PerActivity
    DashboardView provideView(DashboardViewImpl view) {
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
    DashboardController provideController() {
        return mController;
    }

    @Provides
    @PerActivity
    FragmentManager provideFragmentManager() {
        return mFragmentManager;
    }

    @Provides
    @PerActivity
    DashboardPagerAdapter providePagerAdapter(DashboardPagerAdapterImpl adapter) {
        return adapter;
    }

    @Provides
    @PerActivity
    @ForGoogle
    DashboardAnalytics provideGoogleAnalytics(DashboardAnalyticsImpl analytics) {
        return analytics;
    }

    @Provides
    @PerActivity
    @ForFabric
    DashboardAnalytics provideFabricAnalytics(DashboardFabricAnalytics analytics) {
        return analytics;
    }
}
