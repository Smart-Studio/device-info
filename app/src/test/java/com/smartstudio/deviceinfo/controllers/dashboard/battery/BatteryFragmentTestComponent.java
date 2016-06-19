package com.smartstudio.deviceinfo.controllers.dashboard.battery;

import com.smartstudio.deviceinfo.injection.components.BatteryComponent;
import com.smartstudio.deviceinfo.injection.scopes.PerFragment;

import dagger.Component;

@PerFragment
@Component(modules = BatteryFragmentTestModule.class)
public interface BatteryFragmentTestComponent extends BatteryComponent {
    void inject(BatteryFragmentUnitTest test);
}
