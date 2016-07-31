package com.smartstudio.deviceinfo.analytics.dashboard;

import android.os.Bundle;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.smartstudio.deviceinfo.analytics.FirebaseAnalyticsManagerImpl;

import javax.inject.Inject;
import javax.inject.Provider;

public class DashboardFirebaseAnalyticsImpl extends FirebaseAnalyticsManagerImpl implements DashboardAnalytics {
    static final String SCREEN_NAME = "Dashboard";
    static final String TAP_SHARE = "Tap menu share";
    static final String TAP_ABOUT = "Tap menu about";
    static final String MENU_OPTIONS_OPENED = "Menu options opened";
    static final String MENU_OPTIONS_CLOSED = "Menu options closed";

    /**
     * Creates an instance of the AnalyticsManagerImpl
     *
     **/
    @Inject
    DashboardFirebaseAnalyticsImpl(FirebaseAnalytics analytics, Provider<Bundle> bundleProvider) {
        super(analytics, bundleProvider);
    }

    @Override
    protected String getScreenName() {
        return SCREEN_NAME;
    }

    @Override
    public void reportShareTap() {
        reportEvent(TAP_SHARE);
    }

    @Override
    public void reportAboutTap() {
        reportEvent(TAP_ABOUT);
    }

    @Override
    public void reportOptionsMenuOpened() {
        reportEvent(MENU_OPTIONS_OPENED);
    }

    @Override
    public void reportOptionsMenuClosed() {
        reportEvent(MENU_OPTIONS_CLOSED);
    }
}
