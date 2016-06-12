package com.smartstudio.deviceinfo.ui.dashboard.battery;

import com.smartstudio.deviceinfo.model.BatteryState;
import com.smartstudio.deviceinfo.ui.BaseView;

public interface BatteryView extends BaseView {
    void showBatteryState(BatteryState state);
}
