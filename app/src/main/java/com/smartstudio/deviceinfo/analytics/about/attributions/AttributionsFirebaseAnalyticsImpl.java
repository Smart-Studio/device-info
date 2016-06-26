package com.smartstudio.deviceinfo.analytics.about.attributions;


import android.os.Bundle;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.smartstudio.deviceinfo.analytics.FirebaseAnalyticsManagerImpl;

import javax.inject.Inject;
import javax.inject.Provider;

public class AttributionsFirebaseAnalyticsImpl extends FirebaseAnalyticsManagerImpl implements AttributionsAnalytics {
    static final String SCREEN_NAME = "Attributions";
    static final String TAP_ATTRIBUTION = "Tap attribution";
    static final String TAP_ACTION_BAR_BACK = "Tap action bar back";
    static final String PARAM_LIBRARY = "Library";

    /**
     * Creates an instance of the AnalyticsManagerImpl
     **/
    @Inject
    public AttributionsFirebaseAnalyticsImpl(FirebaseAnalytics analytics, Provider<Bundle> bundleProvider) {
        super(analytics, bundleProvider);
    }

    @Override
    protected String getScreenName() {
        return SCREEN_NAME;
    }

    @Override
    public void reportAttributionTap(String library) {
        Bundle bundle = createBaseEventBundle();
        bundle.putString(PARAM_LIBRARY, library);

        reportEvent(TAP_ATTRIBUTION, bundle);
    }

    @Override
    public void reportActionBarBackTap() {
        reportEvent(TAP_ACTION_BAR_BACK);
    }
}
