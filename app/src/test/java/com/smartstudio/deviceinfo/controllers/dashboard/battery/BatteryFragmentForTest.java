package com.smartstudio.deviceinfo.controllers.dashboard.battery;

import android.content.Context;

public class BatteryFragmentForTest extends BatteryFragment {
    BatteryFragmentTestComponent mComponent;

    @Override
    protected void initComponent(Context context) {
        mComponent = DaggerBatteryFragmentTestComponent.builder()
                .batteryFragmentTestModule(new BatteryFragmentTestModule())
                .build();

        mComponent.inject(this);
    }
}
