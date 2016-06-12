package com.smartstudio.deviceinfo.controllers.dashboard.battery;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.smartstudio.deviceinfo.controllers.BaseFragment;
import com.smartstudio.deviceinfo.controllers.dashboard.DashboardActivity;
import com.smartstudio.deviceinfo.injection.Injector;
import com.smartstudio.deviceinfo.logic.dashboard.battery.BatteryStateProvider;
import com.smartstudio.deviceinfo.logic.dashboard.battery.BatteryStateProviderListener;
import com.smartstudio.deviceinfo.model.BatteryState;
import com.smartstudio.deviceinfo.ui.dashboard.battery.BatteryView;

import javax.inject.Inject;

public class BatteryFragment extends BaseFragment implements BatteryController, BatteryStateProviderListener {

    public static BatteryFragment newInstance() {
        return new BatteryFragment();
    }

    @Inject
    BatteryStateProvider mProvider;
    @Inject
    BatteryView mView;

    @Override
    protected void initComponent(Context context) {
        DashboardActivity activity = (DashboardActivity) context;
        activity.getComponent()
                .plus(Injector.provideBatteryModule())
                .inject(this);

        mProvider.setListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        mProvider.requestBatteryUpdates();
    }

    @Override
    public void onStop() {
        super.onStop();
        mProvider.stopBatteryUpdates();
    }

    @Override
    public void onSharedClicked() {

    }

    @Override
    public void onBatteryStateChanged(BatteryState state) {
        mView.showBatteryState(state);
    }
}
