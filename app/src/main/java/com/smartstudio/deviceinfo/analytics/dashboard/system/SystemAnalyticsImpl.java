package com.smartstudio.deviceinfo.analytics.dashboard.system;


import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.smartstudio.deviceinfo.analytics.AnalyticsManagerImpl;

import javax.inject.Inject;
import javax.inject.Provider;

public class SystemAnalyticsImpl extends AnalyticsManagerImpl implements SystemAnalytics {
    static final String SCREEN_NAME = "System info";
    static final String SHARE = "Share system info";

    /**
     * Creates an instance of the AnalyticsManagerImpl
     *
     * @param tracker                   Google analytics tracker singleton instance
     * @param screenViewBuilderProvider Provides a screen view builder to track screens
     * @param eventBuilderProvider      Provides an event builder to track events
     **/
    @Inject
    public SystemAnalyticsImpl(Tracker tracker, Provider<HitBuilders.ScreenViewBuilder> screenViewBuilderProvider,
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
