package com.smartstudio.deviceinfo.analytics;

import android.os.Bundle;

import com.google.firebase.analytics.FirebaseAnalytics;

import javax.inject.Provider;

import timber.log.Timber;

/**
 * AnalyticsManager implementation using Google Analytics
 */
public abstract class FirebaseAnalyticsManagerImpl implements AnalyticsManager {
    static final String EVENT_VIEW = "view";
    static final String PARAM_SCREEN = "screen";

    private final FirebaseAnalytics mAnalytics;
    private final Provider<Bundle> mBundleProvider;
    protected String mScreenName;

    /**
     * Creates an instance of the AnalyticsManagerImpl
     *
     * @param analytics Google analytics tracker singleton instance
     **/
    public FirebaseAnalyticsManagerImpl(FirebaseAnalytics analytics,
                                        Provider<Bundle> bundleProvider) {
        mAnalytics = analytics;
        mBundleProvider = bundleProvider;
    }

    @Override
    public void reportScreen() {
        setScreenName();
        Bundle bundle = createBaseEventBundle();
        mAnalytics.logEvent(EVENT_VIEW, bundle);
        Timber.d("GAnalytics Screen tracked: %s", mScreenName);
    }

    protected void setScreenName() {
        mScreenName = getScreenName();
    }

    /**
     * Gets the name of the screen to be tracked
     *
     * @return Name of the screen
     **/
    protected abstract String getScreenName();

    /**
     * Tracks an event
     *
     * @param event Action to track
     **/
    protected void reportEvent(String event) {
        Bundle bundle = createBaseEventBundle();
        mAnalytics.logEvent(event, bundle);
        Timber.d("GAnalytics Event tracked: %s - %s", mScreenName, event);
    }

    protected void reportEvent(String event, Bundle bundle) {
        mAnalytics.logEvent(event, bundle);
        Timber.d("GAnalytics Event tracked: %s - %s", mScreenName, bundle.toString());
    }

    /***
     * Creates a bundle with the basic information to track an event
     *
     * @return Bundle setup with the basic info required
     **/
    protected Bundle createBaseEventBundle() {
        Bundle bundle = mBundleProvider.get();
        bundle.putString(PARAM_SCREEN, mScreenName);
        return bundle;
    }
}