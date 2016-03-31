package com.smartstudio.deviceinfo.analytics;


import com.crashlytics.android.answers.Answers;
import com.crashlytics.android.answers.ContentViewEvent;
import com.crashlytics.android.answers.CustomEvent;

import timber.log.Timber;

public abstract class FabricAnalyticsManager implements AnalyticsManager {
    private static final String SCREEN = "Screen";

    private final Answers mAnswers;
    protected final String mScreenName;
    private final ContentViewEvent mContentViewEvent;


    public FabricAnalyticsManager(Answers answers, ContentViewEvent contentViewEvent) {
        mAnswers = answers;
        mScreenName = getScreenName();
        mContentViewEvent = contentViewEvent;
        mContentViewEvent.putContentName(mScreenName);
    }

    @Override
    public void reportScreen() {
        setupContentViewEvent(mContentViewEvent);
        mAnswers.logContentView(mContentViewEvent);
        Timber.d("Fabric Analytics Screen tracked: %s", mScreenName);
    }

    /**
     * Gets the name of the screen to be tracked
     *
     * @return Name of the screen
     **/
    protected abstract String getScreenName();

    protected abstract void setupContentViewEvent(ContentViewEvent event);

    /**
     * Reports an event
     *
     * @param action Action to report
     **/
    protected void reportEvent(String action) {
        CustomEvent event = new CustomEvent(action);
        event.putCustomAttribute(SCREEN, mScreenName);
        reportEvent(event);
        Timber.d("Fabric Analytics Event tracked: %s - %s", mScreenName, action);
    }

    /**
     * Tracks an event
     *
     * @param action Action to track
     **/
    protected CustomEvent buildCustomEvent(String action) {
        CustomEvent event = new CustomEvent(action);
        event.putCustomAttribute(SCREEN, mScreenName);
        return event;
    }

    protected void reportEvent(CustomEvent event) {
        mAnswers.logCustom(event);
    }
}
