package com.smartstudio.deviceinfo.analytics.dashboard;

import com.smartstudio.deviceinfo.analytics.AnalyticsManager;

public interface DashboardAnalytics extends AnalyticsManager{
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