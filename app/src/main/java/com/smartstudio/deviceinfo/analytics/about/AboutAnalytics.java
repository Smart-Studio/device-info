package com.smartstudio.deviceinfo.analytics.about;

import com.smartstudio.deviceinfo.analytics.AnalyticsManager;

public interface AboutAnalytics extends AnalyticsManager {
    /**
     * Reports when open source link is tapped
     **/
    void reportOpenSourceTap();

    /**
     * Reports when the attribution button is tapped
     **/
    void reportAttributionsTap();

    /**
     * Reports when the back arrow in the action bar is tapped
     **/
    void reportActionBarBackTap();
}
