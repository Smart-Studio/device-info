package com.smartstudio.deviceinfo.ui.dashboard.battery;

import android.content.res.Resources;
import android.os.BatteryManager;
import android.support.annotation.StringRes;

import com.smartstudio.deviceinfo.R;
import com.smartstudio.deviceinfo.model.BatteryState;
import com.smartstudio.deviceinfo.ui.BaseViewImpl;
import com.smartstudio.deviceinfo.ui.PropertyLayout;

import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;

public class BatteryViewImpl extends BaseViewImpl implements BatteryView {
    @BindView(R.id.view_battery_level)
    PropertyLayout mViewLevel;

    @BindView(R.id.view_battery_health)
    PropertyLayout mViewHealth;

    @BindView(R.id.view_battery_source)
    PropertyLayout mViewSource;

    @BindView(R.id.view_battery_status)
    PropertyLayout mViewStatus;

    @BindView(R.id.view_battery_capacity)
    PropertyLayout mViewCapacity;

    @BindView(R.id.view_battery_type)
    PropertyLayout mViewType;

    @BindView(R.id.view_battery_temperature)
    PropertyLayout mViewTemperature;

    @BindView(R.id.view_battery_voltage)
    PropertyLayout mViewVoltage;

    private final Resources mResources;


    @Inject
    public BatteryViewImpl(Resources resources) {
        mResources = resources;
    }

    @Override
    public int getLayoutResourceId() {
        return R.layout.fragment_battery;
    }

    @Override
    public void showBatteryState(BatteryState state) {
        setLevel(state.getLevel());
        setViewHealth(state.getHealth());
        setViewSource(state.getSource(), state.isCharging());
        setViewStatus(state.getStatus());
        setViewCapacity(state.getCapacity());
        setViewType(state.getType());
        setViewTemperature(state.getTemperature());
        setViewVoltage(state.getVoltage());
    }

    private void setLevel(int levelValue) {
        String level = levelValue + " %";
        mViewLevel.setValue(level);
    }

    private void setViewHealth(int healthValue) {
        String health;

        switch (healthValue) {
            case BatteryManager.BATTERY_HEALTH_GOOD:
                health = getString(R.string.battery_good);
                break;
            case BatteryManager.BATTERY_HEALTH_DEAD:
                health = getString(R.string.battery_dead);
                break;
            case BatteryManager.BATTERY_HEALTH_COLD:
                health = getString(R.string.battery_cold);
                break;
            case BatteryManager.BATTERY_HEALTH_OVERHEAT:
                health = getString(R.string.battery_overheat);
                break;
            case BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE:
                health = getString(R.string.battery_over_voltage);
                break;
            case BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE:
                health = getString(R.string.battery_unspecified);
                break;
            case BatteryManager.BATTERY_HEALTH_UNKNOWN:
            default:
                health = getString(R.string.unknown);
        }

        mViewHealth.setValue(health);
    }

    private void setViewSource(int sourceValue, boolean isCharging) {
        if (!isCharging) {
            mViewSource.setValue(getString(R.string.battery_source_battery));
            return;
        }

        String source;

        switch (sourceValue) {
            case BatteryManager.BATTERY_PLUGGED_USB:
                source = getString(R.string.battery_source_usb);
                break;
            case BatteryManager.BATTERY_PLUGGED_AC:
                source = getString(R.string.battery_source_ac);
                break;
            case BatteryManager.BATTERY_PLUGGED_WIRELESS:
                source = getString(R.string.battery_source_wireless);
                break;
            default:
                source = getString(R.string.unknown);
        }

        mViewSource.setValue(source);
    }

    private void setViewStatus(int statusValue) {
        String status;

        switch (statusValue) {
            case BatteryManager.BATTERY_STATUS_CHARGING:
                status = getString(R.string.battery_charging);
                break;
            case BatteryManager.BATTERY_STATUS_DISCHARGING:
                status = getString(R.string.battery_discharging);
                break;
            case BatteryManager.BATTERY_STATUS_FULL:
                status = getString(R.string.battery_full);
                break;
            case BatteryManager.BATTERY_STATUS_NOT_CHARGING:
                status = getString(R.string.battery_not_charging);
                break;
            case BatteryManager.BATTERY_STATUS_UNKNOWN:
            default:
                status = getString(R.string.unknown);
        }

        mViewStatus.setValue(status);
    }

    private void setViewCapacity(double capacityValue) {
        String capacity = String.format(Locale.getDefault(), "%d mAh", (int) capacityValue);
        mViewCapacity.setValue(capacity);
    }

    private void setViewType(String type) {
        mViewType.setValue(type);
    }

    private void setViewTemperature(double temperatureValue) {
        String temperature = String.format(Locale.getDefault(), "%.1f ℃", temperatureValue);
        mViewTemperature.setValue(temperature);
    }

    private void setViewVoltage(int voltageValue) {
        String voltage = String.format(Locale.getDefault(), "%d mV", voltageValue);
        mViewVoltage.setValue(voltage);
    }

    private String getString(@StringRes int resId) {
        return mResources.getString(resId);
    }
}
