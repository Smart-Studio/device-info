package com.smartstudio.deviceinfo.controllers.dashboard;


import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.smartstudio.deviceinfo.DeviceInfoApp;
import com.smartstudio.deviceinfo.R;
import com.smartstudio.deviceinfo.analytics.dashboard.DashboardAnalytics;
import com.smartstudio.deviceinfo.controllers.BaseActivity;
import com.smartstudio.deviceinfo.controllers.about.AboutActivity;
import com.smartstudio.deviceinfo.injection.Injector;
import com.smartstudio.deviceinfo.injection.components.DashboardComponent;
import com.smartstudio.deviceinfo.injection.qualifiers.ForFabric;
import com.smartstudio.deviceinfo.injection.qualifiers.ForGoogle;
import com.smartstudio.deviceinfo.ui.dashboard.DashboardView;

import javax.inject.Inject;

public class DashboardActivity extends BaseActivity implements DashboardController, ActionBar.OnMenuVisibilityListener {
    @Inject
    DashboardView mView;

    @Inject
    @ForGoogle
    DashboardAnalytics mAnalytics;

    @Inject
    @ForFabric
    DashboardAnalytics mFabricAnalytics;

    private DashboardComponent mComponent;

    @Override
    protected void initComponent() {
        mComponent = DeviceInfoApp.get()
                .getComponent()
                .plus(Injector.provideDashboardModule(this, this, getSupportFragmentManager()));
        mComponent.inject(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.about:
                AboutActivity.launch(this);
                mAnalytics.reportAboutTap();
                mFabricAnalytics.reportAboutTap();
                break;
        }
        return true;
    }

    @Override
    public ActionBar setUpToolbar(Toolbar toolbar) {
        ActionBar actionBar = super.setUpToolbar(toolbar);
        actionBar.addOnMenuVisibilityListener(this);
        return actionBar;
    }

    @Override
    public void onMenuVisibilityChanged(boolean isVisible) {
        if (isVisible) {
            mAnalytics.reportOptionsMenuOpened();
            mFabricAnalytics.reportOptionsMenuOpened();
        } else {
            mAnalytics.reportOptionsMenuClosed();
            mFabricAnalytics.reportOptionsMenuClosed();
        }
    }

    public DashboardComponent getComponent() {
        return mComponent;
    }
}
