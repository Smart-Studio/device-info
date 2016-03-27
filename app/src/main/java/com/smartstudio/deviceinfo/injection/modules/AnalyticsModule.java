package com.smartstudio.deviceinfo.injection.modules;


import android.app.Application;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.smartstudio.deviceinfo.BuildConfig;
import com.smartstudio.deviceinfo.injection.scopes.PerApplication;

import dagger.Module;
import dagger.Provides;

/**
 * Module that provides analytics dependencies
 */
@Module
public class AnalyticsModule {

    @Provides
    @PerApplication
    Tracker provideAnalyticsTracker(GoogleAnalytics analytics) {
        String trackingId = BuildConfig.DEBUG ?
                BuildConfig.ANALYTICS_DEBUG_TRACKING_ID : BuildConfig.ANALYTICS_TRACKING_ID;

        Tracker tracker = analytics.newTracker(trackingId);
        tracker.enableExceptionReporting(true);
        tracker.enableAdvertisingIdCollection(true);
        tracker.setUseSecure(!BuildConfig.DEBUG);

        return tracker;
    }

    @Provides
    @PerApplication
    GoogleAnalytics provideAnalytics(Application app) {
        return GoogleAnalytics.getInstance(app);
    }

    @Provides
    HitBuilders.ScreenViewBuilder provideScreenViewBuilder() {
        return new HitBuilders.ScreenViewBuilder();
    }

    @Provides
    HitBuilders.EventBuilder provideEventViewBuilder() {
        return new HitBuilders.EventBuilder();
    }
}
