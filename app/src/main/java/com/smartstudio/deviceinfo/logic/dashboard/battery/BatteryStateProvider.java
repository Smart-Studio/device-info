package com.smartstudio.deviceinfo.logic.dashboard.battery;

public interface BatteryStateProvider {

    void requestBatteryUpdates();

    void stopBatteryUpdates();

    void setListener(BatteryStateProviderListener listener);
}
