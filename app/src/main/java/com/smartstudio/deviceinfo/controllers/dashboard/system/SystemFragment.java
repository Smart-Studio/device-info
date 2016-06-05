package com.smartstudio.deviceinfo.controllers.dashboard.system;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.smartstudio.deviceinfo.analytics.dashboard.system.SystemAnalytics;
import com.smartstudio.deviceinfo.controllers.BaseFragment;
import com.smartstudio.deviceinfo.controllers.dashboard.DashboardActivity;
import com.smartstudio.deviceinfo.injection.Injector;
import com.smartstudio.deviceinfo.injection.qualifiers.ForFabric;
import com.smartstudio.deviceinfo.injection.qualifiers.ForGoogle;
import com.smartstudio.deviceinfo.logic.dashboard.SystemInfoProvider;
import com.smartstudio.deviceinfo.ui.dashboard.system.SystemView;

import javax.inject.Inject;

public class SystemFragment extends BaseFragment implements SystemController {

    @Inject
    SystemView mView;

    @Inject
    SystemInfoProvider mProvider;

    @Inject
    @ForGoogle
    SystemAnalytics mAnalytics;

    @Inject
    @ForFabric
    SystemAnalytics mFabricAnalytics;

    public static SystemFragment newInstance() {
        return new SystemFragment();
    }

    @Override
    protected void initComponent(Context context) {
        DashboardActivity activity = (DashboardActivity) context;
        activity.getComponent()
                .plus(Injector.provideSystemModule(this))
                .inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(mView.getLayoutResourceId(), container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mView.showSystemInfo(mProvider.getSystemInfo());
    }

    @Override
    public void onResume() {
        super.onResume();
        mAnalytics.reportScreen();
        mFabricAnalytics.reportScreen();
    }

    @Override
    public void onSharedClicked() {
        mView.showShareDialog();
        mAnalytics.reportShare();
        mFabricAnalytics.reportShare();
    }
}
