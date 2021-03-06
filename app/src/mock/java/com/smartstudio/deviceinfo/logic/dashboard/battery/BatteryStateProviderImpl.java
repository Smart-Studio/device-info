package com.smartstudio.deviceinfo.logic.dashboard.battery;

import android.os.BatteryManager;

import com.smartstudio.deviceinfo.model.BatteryState;

import javax.inject.Inject;

public class BatteryStateProviderImpl implements BatteryStateProvider {
    private static final int LEVEL = 59;
    private static final int HEALTH = BatteryManager.BATTERY_HEALTH_DEAD;
    private static final int POWER_SOURCE = BatteryManager.BATTERY_PLUGGED_AC;
    private static final int STATUS = BatteryManager.BATTERY_STATUS_CHARGING;
    private static final int CAPACITY = 2569;
    private static final String TYPE = "Plutonium";
    private static final int TEMPERATURE = -142;
    private static final int VOLTAGE = 293;


    private BatteryStateProviderListener mListener;
    private BatteryState mBatteryState;

    @Inject
    public BatteryStateProviderImpl() {
        mBatteryState = new BatteryState();
        mBatteryState.setLevel(LEVEL);
        mBatteryState.setHealth(HEALTH);
        mBatteryState.setCharging(true);
        mBatteryState.setSource(POWER_SOURCE);
        mBatteryState.setStatus(STATUS);
        mBatteryState.setCapacity(CAPACITY);
        mBatteryState.setType(TYPE);
        mBatteryState.setTemperature(TEMPERATURE);
        mBatteryState.setVoltage(VOLTAGE);
    }

    @Override
    public void requestBatteryStateUpdates() {
        mListener.onBatteryStateChanged(mBatteryState);
    }

    @Override
    public void stopBatteryStateUpdates() {

    }

    @Override
    public void setListener(BatteryStateProviderListener listener) {
        mListener = listener;
    }
}
