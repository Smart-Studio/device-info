package com.smartstudio.deviceinfo.analytics;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import javax.inject.Provider;

import timber.log.Timber;

/**
 * AnalyticsManager implementation using Google Analytics
 */
public abstract class AnalyticsManagerImpl implements AnalyticsManager {
    protected final Tracker mTracker;
    private Provider<HitBuilders.ScreenViewBuilder> mScreenViewBuilderProvider;
    private Provider<HitBuilders.EventBuilder> mEventBuilderProvider;
    protected String mScreenName;

    /**
     * Creates an instance of the AnalyticsManagerImpl
     *
     * @param tracker                   Google analytics tracker singleton instance
     * @param screenViewBuilderProvider Provides a screen view builder to track screens
     * @param eventBuilderProvider      Provides an event builder to track events
     **/
    public AnalyticsManagerImpl(Tracker tracker,
                                Provider<HitBuilders.ScreenViewBuilder> screenViewBuilderProvider,
                                Provider<HitBuilders.EventBuilder> eventBuilderProvider) {
        mTracker = tracker;
        mScreenViewBuilderProvider = screenViewBuilderProvider;
        mEventBuilderProvider = eventBuilderProvider;
    }

    @Override
    public void reportScreen() {
        setScreenName();
        mTracker.send(mScreenViewBuilderProvider.get().build());
        Timber.d("GAnalytics Screen tracked: %s", mScreenName);
    }

    /**
     * Sets the name of the screen to be tracked (Doesn't actually track a screen view)
     * Needed because the {@link Tracker} instance is shared between all the managers and
     * needs to change the screen to be tracked dynamically
     **/
    protected void setScreenName() {
        mScreenName = getScreenName();
        mTracker.setScreenName(mScreenName);
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
     * @param action Action to track
     **/
    protected void reportEvent(String action) {
        mTracker.send(createBaseEventBuilder(action).build());
        Timber.d("GAnalytics Event tracked: %s - %s", mScreenName, action);
    }

    /**
     * Tracks an event
     *
     * @param action Action to track
     * @param label  Label for the given event
     **/
    protected void reportEvent(String action, String label) {
        HitBuilders.EventBuilder eventBuilder = createBaseEventBuilder(action);
        eventBuilder.setLabel(label);
        mTracker.send(eventBuilder.build());
        Timber.d("GAnalytics Event tracked: %s - %s - %s", mScreenName, action, label);
    }

    /***
     * Creates and configures a {@link com.google.android.gms.analytics.HitBuilders.EventBuilder} with
     * the basic information to track an event
     *
     * @param action Action to track
     * @return Event builder setup with the basic info required
     **/
    private HitBuilders.EventBuilder createBaseEventBuilder(String action) {
        return mEventBuilderProvider.get()
                .setCategory(mScreenName)
                .setAction(action);
    }
}