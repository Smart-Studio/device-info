package com.smartstudio.deviceinfo.analytics.about;


import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.smartstudio.deviceinfo.analytics.AnalyticsManagerImpl;

import javax.inject.Inject;
import javax.inject.Provider;

public class AboutAnalyticsImpl extends AnalyticsManagerImpl implements AboutAnalytics {
    static final String SCREEN_NAME = "About";
    static final String OPEN_SOURCE_TAP = "Open source link tap";
    static final String ATTRIBUTIONS_TAP = "Attributions tap";
    static final String ACTION_BAR_BACK_TAP = "Action bar back tap";

    /**
     * Creates an instance of the AnalyticsManagerImpl
     *
     * @param analytics
     * @param tracker                   Google analytics tracker singleton instance
     * @param screenViewBuilderProvider Provides a screen view builder to track screens
     * @param eventBuilderProvider      Provides an event builder to track events
     **/
    @Inject
    public AboutAnalyticsImpl(GoogleAnalytics analytics, Tracker tracker,
                              Provider<HitBuilders.ScreenViewBuilder> screenViewBuilderProvider,
                              Provider<HitBuilders.EventBuilder> eventBuilderProvider) {
        super(analytics, tracker, screenViewBuilderProvider, eventBuilderProvider);
    }

    @Override
    protected String getScreenName() {
        return SCREEN_NAME;
    }

    @Override
    public void reportOpenSourceTap() {
        reportEvent(OPEN_SOURCE_TAP);
    }

    @Override
    public void reportAttributionsTap() {
        reportEvent(ATTRIBUTIONS_TAP);
    }

    @Override
    public void reportActionBarBackTap() {
        reportEvent(ACTION_BAR_BACK_TAP);
    }
}
