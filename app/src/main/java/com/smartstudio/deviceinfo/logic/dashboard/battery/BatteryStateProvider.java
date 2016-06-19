package com.smartstudio.deviceinfo.logic.dashboard.battery;

public interface BatteryStateProvider {

    void requestBatteryStateUpdates();

    void stopBatteryStateUpdates();

    void setListener(BatteryStateProviderListener listener);
}
