package com.smartstudio.deviceinfo.controllers.dashboard.battery;

import com.smartstudio.deviceinfo.R;
import com.smartstudio.deviceinfo.injection.qualifiers.ForFragment;
import com.smartstudio.deviceinfo.injection.scopes.PerFragment;
import com.smartstudio.deviceinfo.logic.dashboard.battery.BatteryStateProvider;
import com.smartstudio.deviceinfo.ui.BaseView;
import com.smartstudio.deviceinfo.ui.dashboard.battery.BatteryView;

import dagger.Module;
import dagger.Provides;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Module
public class BatteryFragmentTestModule {

    @Provides
    @PerFragment
    BatteryView provideView() {
        BatteryView view = mock(BatteryView.class);
        when(view.getLayoutResourceId()).thenReturn(R.layout.fragment_battery);
        return view;
    }

    @Provides
    @PerFragment
    @ForFragment
    BaseView provideBaseView(BatteryView view) {
        return view;
    }


    @Provides
    @PerFragment
    BatteryStateProvider provideProvider() {
        return mock(BatteryStateProvider.class);
    }
}
