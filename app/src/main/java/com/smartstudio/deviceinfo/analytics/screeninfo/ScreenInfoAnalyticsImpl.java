package com.smartstudio.deviceinfo.analytics.screeninfo;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.smartstudio.deviceinfo.analytics.AnalyticsManagerImpl;

import javax.inject.Inject;
import javax.inject.Provider;

public class ScreenInfoAnalyticsImpl extends AnalyticsManagerImpl implements ScreenInfoAnalytics {
    static final String SCREEN_NAME = "Screen info";
    static final String TAP_ABOUT = "Tap menu about";
    static final String MENU_OPTIONS_OPENED = "Menu options opened";
    static final String MENU_OPTIONS_CLOSED = "Menu options closed";

    /**
     * Creates an instance of the AnalyticsManagerImpl
     *
     * @param tracker                   Google analytics tracker singleton instance
     * @param screenViewBuilderProvider Provides a screen view builder to track screens
     * @param eventBuilderProvider      Provides an event builder to track events
     **/
    @Inject
    public ScreenInfoAnalyticsImpl(GoogleAnalytics analytics, Tracker tracker,
                                   Provider<HitBuilders.ScreenViewBuilder> screenViewBuilderProvider,
                                   Provider<HitBuilders.EventBuilder> eventBuilderProvider) {
        super(analytics, tracker, screenViewBuilderProvider, eventBuilderProvider);
    }

    @Override
    protected String getScreenName() {
        return SCREEN_NAME;
    }

    @Override
    public void reportAboutTap() {
        reportEvent(TAP_ABOUT);
    }

    @Override
    public void reportOptionsMenuOpened() {
        reportEvent(MENU_OPTIONS_OPENED);
    }

    @Override
    public void reportOptionsMenuClosed() {
        reportEvent(MENU_OPTIONS_CLOSED);
    }
}
