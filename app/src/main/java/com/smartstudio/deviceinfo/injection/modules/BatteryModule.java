package com.smartstudio.deviceinfo.injection.modules;

import com.smartstudio.deviceinfo.injection.qualifiers.ForFragment;
import com.smartstudio.deviceinfo.injection.scopes.PerFragment;
import com.smartstudio.deviceinfo.logic.dashboard.battery.BatteryStateProvider;
import com.smartstudio.deviceinfo.logic.dashboard.battery.BatteryStateProviderImpl;
import com.smartstudio.deviceinfo.model.BatteryState;
import com.smartstudio.deviceinfo.ui.BaseView;
import com.smartstudio.deviceinfo.ui.dashboard.battery.BatteryView;
import com.smartstudio.deviceinfo.ui.dashboard.battery.BatteryViewImpl;

import dagger.Module;
import dagger.Provides;

@Module
public class BatteryModule {


    public BatteryModule() {

    }

    @Provides
    @PerFragment
    BatteryView provideView(BatteryViewImpl view) {
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
    BatteryStateProvider provideBatteryStateProvider(BatteryStateProviderImpl provider) {
        return provider;
    }

    @Provides
    BatteryState provideBatteryState() {
        return new BatteryState();
    }
}
