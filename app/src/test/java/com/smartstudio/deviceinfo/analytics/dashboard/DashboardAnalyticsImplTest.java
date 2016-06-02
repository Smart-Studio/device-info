package com.smartstudio.deviceinfo.analytics.dashboard;

import com.smartstudio.deviceinfo.analytics.AnalyticsManagerImpl;
import com.smartstudio.deviceinfo.analytics.AnalyticsManagerImplTest;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class DashboardAnalyticsImplTest extends AnalyticsManagerImplTest {
    private DashboardAnalyticsImpl mAnalytics;

    @Test
    public void testGetScreenName() throws Exception {
        String screenName = mAnalytics.getScreenName();
        assertThat(screenName).isEqualTo(DashboardAnalyticsImpl.SCREEN_NAME);
    }

    @Test
    public void testReportAboutTap() throws Exception {
        setScreenName();
        mAnalytics.reportAboutTap();
        verifyEvent(DashboardAnalyticsImpl.SCREEN_NAME, DashboardAnalyticsImpl.TAP_ABOUT);
    }

    @Test
    public void testReportOptionsMenuOpened() throws Exception {
        setScreenName();
        mAnalytics.reportOptionsMenuOpened();
        verifyEvent(DashboardAnalyticsImpl.SCREEN_NAME, DashboardAnalyticsImpl.MENU_OPTIONS_OPENED);
    }

    @Test
    public void testReportOptionsMenuClosed() throws Exception {
        setScreenName();
        mAnalytics.reportOptionsMenuClosed();
        verifyEvent(DashboardAnalyticsImpl.SCREEN_NAME, DashboardAnalyticsImpl.MENU_OPTIONS_CLOSED);
    }

    @Override
    protected AnalyticsManagerImpl createAnalyticsManager() {
        mAnalytics = new DashboardAnalyticsImpl(mTracker, mScreenViewBuilderProvider, mEventBuilderProvider);
        return mAnalytics;
    }
}