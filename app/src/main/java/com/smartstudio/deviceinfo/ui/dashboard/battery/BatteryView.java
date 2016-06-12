package com.smartstudio.deviceinfo.ui.dashboard.battery;

import com.smartstudio.deviceinfo.model.BatteryState;
import com.smartstudio.deviceinfo.ui.dashboard.DashboardContentView;

public interface BatteryView extends DashboardContentView {
    void showBatteryState(BatteryState state);
}
