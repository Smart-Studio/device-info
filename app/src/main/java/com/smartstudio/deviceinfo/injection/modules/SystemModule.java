package com.smartstudio.deviceinfo.injection.modules;

import com.smartstudio.deviceinfo.analytics.dashboard.system.SystemAnalytics;
import com.smartstudio.deviceinfo.analytics.dashboard.system.SystemAnalyticsImpl;
import com.smartstudio.deviceinfo.analytics.dashboard.system.SystemFabricAnalytics;
import com.smartstudio.deviceinfo.controllers.dashboard.system.SystemController;
import com.smartstudio.deviceinfo.injection.qualifiers.ForFabric;
import com.smartstudio.deviceinfo.injection.qualifiers.ForFragment;
import com.smartstudio.deviceinfo.injection.qualifiers.ForGoogle;
import com.smartstudio.deviceinfo.injection.scopes.PerFragment;
import com.smartstudio.deviceinfo.logic.SystemInfoProviderImpl;
import com.smartstudio.deviceinfo.logic.dashboard.SystemInfoProvider;
import com.smartstudio.deviceinfo.logic.dashboard.screeninfo.ScreenInfoShareManager;
import com.smartstudio.deviceinfo.logic.dashboard.system.SystemInfoShareManager;
import com.smartstudio.deviceinfo.logic.dashboard.system.SystemInfoShareManagerImpl;
import com.smartstudio.deviceinfo.ui.BaseView;
import com.smartstudio.deviceinfo.ui.dashboard.system.SystemView;
import com.smartstudio.deviceinfo.ui.dashboard.system.SystemViewImpl;

import dagger.Module;
import dagger.Provides;

@Module
public class SystemModule {

    private final SystemController mController;

    public SystemModule(SystemController controller) {
        mController = controller;
    }

    @Provides
    @PerFragment
    SystemController provideController() {
        return mController;
    }

    @Provides
    @PerFragment
    SystemView provideView(SystemViewImpl view) {
        return view;
    }

    @Provides
    @PerFragment
    @ForFragment
    BaseView provideBaseView(SystemView view) {
        return view;
    }

    @Provides
    @PerFragment
    SystemInfoProvider provideProvider(SystemInfoProviderImpl provider) {
        return provider;
    }

    @Provides
    @PerFragment
    @ForGoogle
    SystemAnalytics provideGoogleAnalytics(SystemAnalyticsImpl analytics) {
        return analytics;
    }

    @Provides
    @PerFragment
    @ForFabric
    SystemAnalytics provideFabricAnalytics(SystemFabricAnalytics analytics) {
        return analytics;
    }

    @Provides
    @PerFragment
    SystemInfoShareManager provideSharer(SystemInfoShareManagerImpl shareManager) {
        return shareManager;
    }
}
