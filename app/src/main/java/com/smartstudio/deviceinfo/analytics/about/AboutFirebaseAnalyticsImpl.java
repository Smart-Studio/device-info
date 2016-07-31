package com.smartstudio.deviceinfo.analytics.about;


import android.os.Bundle;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.smartstudio.deviceinfo.analytics.FirebaseAnalyticsManagerImpl;

import javax.inject.Inject;
import javax.inject.Provider;

public class AboutFirebaseAnalyticsImpl extends FirebaseAnalyticsManagerImpl implements AboutAnalytics {
    static final String SCREEN_NAME = "About";
    static final String TAP_OPEN_SOURCE = "Tap open source link";
    static final String TAP_ATTRIBUTIONS = "Tap attributions";
    static final String TAP_ACTION_BAR_BACK = "Tap action bar back";

    /**
     * Creates an instance of the AnalyticsManagerImpl
     **/
    @Inject
    AboutFirebaseAnalyticsImpl(FirebaseAnalytics analytics, Provider<Bundle> bundleProvider) {
        super(analytics, bundleProvider);
    }

    @Override
    protected String getScreenName() {
        return SCREEN_NAME;
    }

    @Override
    public void reportOpenSourceTap() {
        reportEvent(TAP_OPEN_SOURCE);
    }

    @Override
    public void reportAttributionsTap() {
        reportEvent(TAP_ATTRIBUTIONS);
    }

    @Override
    public void reportActionBarBackTap() {
        reportEvent(TAP_ACTION_BAR_BACK);
    }
}
