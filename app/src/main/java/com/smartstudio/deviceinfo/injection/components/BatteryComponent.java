package com.smartstudio.deviceinfo.injection.components;

import com.smartstudio.deviceinfo.controllers.dashboard.battery.BatteryFragment;
import com.smartstudio.deviceinfo.injection.modules.BatteryModule;
import com.smartstudio.deviceinfo.injection.scopes.PerFragment;

import dagger.Subcomponent;

@PerFragment
@Subcomponent(modules = BatteryModule.class)
public interface BatteryComponent {
    void inject(BatteryFragment fragment);
}
