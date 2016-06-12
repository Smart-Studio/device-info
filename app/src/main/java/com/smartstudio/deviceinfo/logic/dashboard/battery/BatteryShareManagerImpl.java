package com.smartstudio.deviceinfo.logic.dashboard.battery;


import android.content.Context;
import android.content.Intent;
import android.os.Build;

import com.smartstudio.deviceinfo.R;
import com.smartstudio.deviceinfo.injection.qualifiers.ForActivity;
import com.smartstudio.deviceinfo.logic.dashboard.ShareManagerImpl;
import com.smartstudio.deviceinfo.model.BatteryViewModel;

import javax.inject.Inject;

public class BatteryShareManagerImpl extends ShareManagerImpl<BatteryViewModel> implements BatteryShareManager {

    @Inject
    public BatteryShareManagerImpl(Intent intent, @ForActivity Context context) {
        super(intent, context);
    }

    @Override
    protected String getSubject() {
        return Build.MODEL + " " + getString(R.string.share_battery_subject);
    }

    @Override
    protected void buildText() {
        addTitle(getString(R.string.tab_battery));
        addProperty(getString(R.string.battery_level), mSharedObject.getLevel());
        addProperty(getString(R.string.battery_health), mSharedObject.getHealth());
        addProperty(getString(R.string.battery_source), mSharedObject.getPowerSource());
        addProperty(getString(R.string.battery_status), mSharedObject.getStatus());
        addProperty(getString(R.string.battery_capacity), mSharedObject.getCapacity());
        addProperty(getString(R.string.battery_type), mSharedObject.getType());
        addProperty(getString(R.string.battery_temperature), mSharedObject.getTemperature());
        addProperty(getString(R.string.battery_voltage), mSharedObject.getVoltage());
    }

    @Override
    protected String getTitle() {
        return getString(R.string.share_battery_title);
    }
}
