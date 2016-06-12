package com.smartstudio.deviceinfo.ui.dashboard.battery;

import android.content.res.Resources;
import android.os.BatteryManager;
import android.support.annotation.StringRes;

import com.smartstudio.deviceinfo.R;
import com.smartstudio.deviceinfo.logic.dashboard.battery.BatteryShareManager;
import com.smartstudio.deviceinfo.model.BatteryState;
import com.smartstudio.deviceinfo.model.BatteryViewModel;
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

    @Inject
    BatteryShareManager mShareManager;

    private final Resources mResources;
    private final BatteryViewModel mBatteryInfo;


    @Inject
    public BatteryViewImpl(Resources resources, BatteryViewModel batteryInfo) {
        mResources = resources;
        mBatteryInfo = batteryInfo;
    }

    @Override
    public int getLayoutResourceId() {
        return R.layout.fragment_battery;
    }

    @Override
    public void showBatteryState(BatteryState state) {
        setLevel(state.getLevel());
        setHealth(state.getHealth());
        setSource(state.getSource(), state.isCharging());
        setStatus(state.getStatus());
        setCapacity(state.getCapacity());
        setType(state.getType());
        setTemperature(state.getTemperature());
        setVoltage(state.getVoltage());
    }

    private void setLevel(int levelValue) {
        String level = levelValue + " %";
        mViewLevel.setValue(level);
        mBatteryInfo.setLevel(level);
    }

    private void setHealth(int healthValue) {
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
        mBatteryInfo.setHealth(health);
    }

    private void setSource(int sourceValue, boolean isCharging) {
        if (!isCharging) {
            String source = getString(R.string.battery_source_battery);
            mViewSource.setValue(source);
            mBatteryInfo.setPowerSource(source);
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
        mBatteryInfo.setPowerSource(source);
    }

    private void setStatus(int statusValue) {
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
        mBatteryInfo.setStatus(status);
    }

    private void setCapacity(double capacityValue) {
        String capacity = String.format(Locale.getDefault(), "%d mAh", (int) capacityValue);
        mViewCapacity.setValue(capacity);
        mBatteryInfo.setCapacity(capacity);
    }

    private void setType(String type) {
        mViewType.setValue(type);
        mBatteryInfo.setType(type);
    }

    private void setTemperature(double temperatureValue) {
        String temperature = String.format(Locale.getDefault(), "%.1f ℃", temperatureValue);
        mViewTemperature.setValue(temperature);
        mBatteryInfo.setTemperature(temperature);
    }

    private void setVoltage(int voltageValue) {
        String voltage = String.format(Locale.getDefault(), "%d mV", voltageValue);
        mViewVoltage.setValue(voltage);
        mBatteryInfo.setVoltage(voltage);
    }

    @Override
    public void showShareDialog() {
        mShareManager.share(mBatteryInfo);
    }

    private String getString(@StringRes int resId) {
        return mResources.getString(resId);
    }
}
