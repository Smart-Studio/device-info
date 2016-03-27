package com.smartstudio.deviceinfo.analytics.about;


import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.smartstudio.deviceinfo.analytics.AnalyticsManagerImpl;

import javax.inject.Inject;
import javax.inject.Provider;

public class AboutAnalyticsImpl extends AnalyticsManagerImpl implements AboutAnalytics {
    static final String SCREEN_NAME = "About";
    static final String TAP_OPEN_SOURCE = "Tap open source link";
    static final String TAP_ATTRIBUTIONS = "Tap attributions";
    static final String TAP_ACTION_BAR_BACK = "Tap action bar back";

    /**
     * Creates an instance of the AnalyticsManagerImpl
     *
     * @param tracker                   Google analytics tracker singleton instance
     * @param screenViewBuilderProvider Provides a screen view builder to track screens
     * @param eventBuilderProvider      Provides an event builder to track events
     **/
    @Inject
    public AboutAnalyticsImpl(Tracker tracker,
                              Provider<HitBuilders.ScreenViewBuilder> screenViewBuilderProvider,
                              Provider<HitBuilders.EventBuilder> eventBuilderProvider) {
        super(tracker, screenViewBuilderProvider, eventBuilderProvider);
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
