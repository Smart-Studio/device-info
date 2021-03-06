package com.smartstudio.deviceinfo.controllers.dashboard.battery;

import android.content.Context;

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
    BatteryView mView;

    @Inject
    BatteryStateProvider mProvider;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mProvider.setListener(this);
    }

    @Override
    protected void initComponent(Context context) {
        DashboardActivity activity = (DashboardActivity) context;
        activity.getComponent()
                .plus(Injector.provideBatteryModule())
                .inject(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        mProvider.requestBatteryStateUpdates();
    }

    @Override
    public void onStop() {
        super.onStop();
        mProvider.stopBatteryStateUpdates();
    }

    @Override
    public void onSharedClicked() {
        mView.showShareDialog();
    }

    @Override
    public void onBatteryStateChanged(BatteryState state) {
        mView.showBatteryState(state);
    }
}
