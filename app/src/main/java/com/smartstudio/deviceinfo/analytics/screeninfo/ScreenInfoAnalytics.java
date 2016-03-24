package com.smartstudio.deviceinfo.analytics.screeninfo;

import com.smartstudio.deviceinfo.analytics.AnalyticsManager;

public interface ScreenInfoAnalytics extends AnalyticsManager {
    /**
     * Reports when the about menu is tapped
     **/
    void reportAboutTap();

    /**
     * Reports when the options menu is opened
     **/
    void reportOptionsMenuOpened();

    /**
     * Reports when the options menu is closed
     **/
    void reportOptionsMenuClosed();
}
