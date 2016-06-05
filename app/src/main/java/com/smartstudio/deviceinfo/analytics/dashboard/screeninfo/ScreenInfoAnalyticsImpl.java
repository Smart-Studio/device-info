package com.smartstudio.deviceinfo.analytics.dashboard.screeninfo;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.smartstudio.deviceinfo.analytics.AnalyticsManagerImpl;

import javax.inject.Inject;
import javax.inject.Provider;

public class ScreenInfoAnalyticsImpl extends AnalyticsManagerImpl implements ScreenInfoAnalytics {
    static final String SCREEN_NAME = "Screen info";
    static final String SHARE = "Share screen info";

    /**
     * Creates an instance of the AnalyticsManagerImpl
     *
     * @param tracker                   Google analytics tracker singleton instance
     * @param screenViewBuilderProvider Provides a screen view builder to track screens
     * @param eventBuilderProvider      Provides an event builder to track events
     **/
    @Inject
    public ScreenInfoAnalyticsImpl(Tracker tracker,
                                   Provider<HitBuilders.ScreenViewBuilder> screenViewBuilderProvider,
                                   Provider<HitBuilders.EventBuilder> eventBuilderProvider) {
        super(tracker, screenViewBuilderProvider, eventBuilderProvider);
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
