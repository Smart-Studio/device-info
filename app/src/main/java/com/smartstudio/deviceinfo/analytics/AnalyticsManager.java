package com.smartstudio.deviceinfo.analytics;

import android.app.Activity;

/**
 * Reports common analytics events
 */
public interface AnalyticsManager {

    /**
     * Reports when a screen is displayed
     **/
    void reportScreen();

    /**
     * Reports when an activity is started
     **/
    void reportActivityStart(Activity activity);

    /**
     * Reports when an activity is stopped
     **/
    void reportActivityStop(Activity activity);
}
