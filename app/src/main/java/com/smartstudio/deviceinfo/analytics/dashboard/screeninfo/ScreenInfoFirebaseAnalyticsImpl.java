package com.smartstudio.deviceinfo.analytics.dashboard.screeninfo;

import android.os.Bundle;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.smartstudio.deviceinfo.analytics.FirebaseAnalyticsManagerImpl;

import javax.inject.Inject;
import javax.inject.Provider;

public class ScreenInfoFirebaseAnalyticsImpl extends FirebaseAnalyticsManagerImpl implements ScreenInfoAnalytics {
    static final String SCREEN_NAME = "Screen info";
    static final String SHARE = "Share screen info";

    /**
     * Creates an instance of the AnalyticsManagerImpl
     **/
    @Inject
    ScreenInfoFirebaseAnalyticsImpl(FirebaseAnalytics analytics, Provider<Bundle> bundleProvider) {
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
