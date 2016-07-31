package com.smartstudio.deviceinfo.analytics.dashboard.system;


import android.os.Bundle;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.smartstudio.deviceinfo.analytics.FirebaseAnalyticsManagerImpl;

import javax.inject.Inject;
import javax.inject.Provider;

public class SystemFirebaseAnalyticsImpl extends FirebaseAnalyticsManagerImpl implements SystemAnalytics {
    static final String SCREEN_NAME = "System info";
    static final String SHARE = "Share system info";

    /**
     * Creates an instance of the AnalyticsManagerImpl
     **/
    @Inject
    SystemFirebaseAnalyticsImpl(FirebaseAnalytics analytics, Provider<Bundle> bundleProvider) {
        super(analytics, bundleProvider);
    }

    @Override
    protected String getScreenName() {
        return SCREEN_NAME;
    }

    @Override
    public void reportShare() {
        reportEvent(SHARE);
    }
}
