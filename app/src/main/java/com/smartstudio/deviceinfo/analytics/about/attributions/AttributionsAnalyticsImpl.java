package com.smartstudio.deviceinfo.analytics.about.attributions;


import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.smartstudio.deviceinfo.analytics.AnalyticsManagerImpl;

import javax.inject.Inject;
import javax.inject.Provider;

public class AttributionsAnalyticsImpl extends AnalyticsManagerImpl implements AttributionsAnalytics {
    static final String SCREEN_NAME = "Attributions";
    static final String TAP_ATTRIBUTION = "Tap attribution";
    static final String TAP_ACTION_BAR_BACK = "Tap action bar back";

    /**
     * Creates an instance of the AnalyticsManagerImpl
     *
     * @param analytics
     * @param tracker                   Google analytics tracker singleton instance
     * @param screenViewBuilderProvider Provides a screen view builder to track screens
     * @param eventBuilderProvider      Provides an event builder to track events
     **/
    @Inject
    public AttributionsAnalyticsImpl(GoogleAnalytics analytics, Tracker tracker,
                                     Provider<HitBuilders.ScreenViewBuilder> screenViewBuilderProvider,
                                     Provider<HitBuilders.EventBuilder> eventBuilderProvider) {
        super(analytics, tracker, screenViewBuilderProvider, eventBuilderProvider);
    }

    @Override
    protected String getScreenName() {
        return SCREEN_NAME;
    }

    @Override
    public void reportAttributionTap(String library) {
        reportEvent(TAP_ATTRIBUTION, library);
    }

    @Override
    public void reportActionBarBackTap() {
        reportEvent(TAP_ACTION_BAR_BACK);
    }
}
