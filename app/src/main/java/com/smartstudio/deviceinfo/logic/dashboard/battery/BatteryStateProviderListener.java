package com.smartstudio.deviceinfo.logic.dashboard.battery;

import com.smartstudio.deviceinfo.model.BatteryState;

public interface BatteryStateProviderListener {
    void onBatteryStateChanged(BatteryState state);
}
