package com.smartstudio.deviceinfo.analytics.about.attributions;

import com.smartstudio.deviceinfo.analytics.AnalyticsManager;

public interface AttributionsAnalytics extends AnalyticsManager {
    /**
     * Reports when the attribution button is tapped
     *
     * @param library Name of the attribution library tapped
     **/
    void reportAttributionTap(String library);

    /**
     * Reports when the back arrow in the action bar is tapped
     **/
    void reportActionBarBackTap();
}
