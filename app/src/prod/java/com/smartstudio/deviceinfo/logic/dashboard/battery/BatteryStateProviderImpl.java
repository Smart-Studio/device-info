package com.smartstudio.deviceinfo.logic.dashboard.battery;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;

import com.smartstudio.deviceinfo.injection.qualifiers.ForApplication;
import com.smartstudio.deviceinfo.model.BatteryState;

import javax.inject.Inject;

import timber.log.Timber;

public class BatteryStateProviderImpl extends BroadcastReceiver implements BatteryStateProvider {
    private static final String POWER_PROFILE_CLASS = "com.android.internal.os.PowerProfile";
    private static final String METHOD_AVERAGE_POWER = "getAveragePower";
    private static final String BATTERY_SUBSYSTEM = "battery.capacity";
    private static final int INVALID = -1;

    private final Context mContext;
    private final IntentFilter mFilter;
    private final BatteryState mBatteryState;
    private BatteryStateProviderListener mListener;

    @Inject
    public BatteryStateProviderImpl(@ForApplication Context context, IntentFilter filter, BatteryState batteryState) {
        mContext = context;
        mFilter = filter;
        mBatteryState = new BatteryState();
        filter.addAction(Intent.ACTION_BATTERY_CHANGED);
    }

    @Override
    public void requestBatteryUpdates() {
        mContext.registerReceiver(this, mFilter);
    }

    @Override
    public void stopBatteryUpdates() {
        mContext.unregisterReceiver(this);
    }

    @Override
    public void setListener(BatteryStateProviderListener listener) {
        mListener = listener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, INVALID);
        int health = intent.getIntExtra(BatteryManager.EXTRA_HEALTH, INVALID);
        int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, INVALID);
        int source = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, INVALID);
        boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING || status == BatteryManager.BATTERY_STATUS_FULL;
        String type = intent.getStringExtra(BatteryManager.EXTRA_TECHNOLOGY);
        double temperature = intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, INVALID) / 10.0;
        int voltage = intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE, INVALID);

        mBatteryState.setLevel(level);
        mBatteryState.setHealth(health);
        mBatteryState.setCharging(isCharging);
        mBatteryState.setStatus(status);
        mBatteryState.setSource(source);
        mBatteryState.setCapacity(getCapacity());
        mBatteryState.setType(type);
        mBatteryState.setTemperature(temperature);
        mBatteryState.setVoltage(voltage);

        mListener.onBatteryStateChanged(mBatteryState);
    }

    private double getCapacity() {
        Object powerProfile;
        try {
            powerProfile = Class.forName(POWER_PROFILE_CLASS)
                    .getConstructor(Context.class)
                    .newInstance(mContext);

            return (double) (Double) Class
                    .forName(POWER_PROFILE_CLASS)
                    .getMethod(METHOD_AVERAGE_POWER, String.class)
                    .invoke(powerProfile, BATTERY_SUBSYSTEM);
        } catch (Exception e) {
            Timber.e(e, "Error requesting battery capacity");
        }

        return INVALID;
    }

}
