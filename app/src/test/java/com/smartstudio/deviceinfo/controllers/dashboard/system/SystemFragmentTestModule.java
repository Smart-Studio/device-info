package com.smartstudio.deviceinfo.controllers.dashboard.system;

import com.smartstudio.deviceinfo.R;
import com.smartstudio.deviceinfo.analytics.dashboard.system.SystemAnalytics;
import com.smartstudio.deviceinfo.injection.qualifiers.ForFabric;
import com.smartstudio.deviceinfo.injection.qualifiers.ForFragment;
import com.smartstudio.deviceinfo.injection.qualifiers.ForGoogle;
import com.smartstudio.deviceinfo.injection.scopes.PerFragment;
import com.smartstudio.deviceinfo.logic.dashboard.SystemInfoProvider;
import com.smartstudio.deviceinfo.model.SystemInfo;
import com.smartstudio.deviceinfo.ui.BaseView;
import com.smartstudio.deviceinfo.ui.dashboard.system.SystemView;

import dagger.Module;
import dagger.Provides;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Module
public class SystemFragmentTestModule {

    @Provides
    @PerFragment
    SystemView provideView() {
        SystemView view = mock(SystemView.class);
        when(view.getLayoutResourceId()).thenReturn(R.layout.fragment_system);
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
    SystemInfoProvider provideSystemInfoProvider(SystemInfo info) {
        SystemInfoProvider provider = mock(SystemInfoProvider.class);
        when(provider.getSystemInfo()).thenReturn(info);
        return provider;
    }

    @Provides
    @PerFragment
    SystemInfo provideSystemInfo() {
        return mock(SystemInfo.class);
    }

    @Provides
    @PerFragment
    @ForGoogle
    SystemAnalytics provideAnalytics() {
        return mock(SystemAnalytics.class);
    }

    @Provides
    @PerFragment
    @ForFabric
    SystemAnalytics provideFabricAnalytics() {
        return mock(SystemAnalytics.class);
    }

}
