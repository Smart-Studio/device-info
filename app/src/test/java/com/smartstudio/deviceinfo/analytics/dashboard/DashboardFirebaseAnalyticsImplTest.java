package com.smartstudio.deviceinfo.analytics.dashboard;

import com.smartstudio.deviceinfo.analytics.FirebaseAnalyticsManagerImpl;
import com.smartstudio.deviceinfo.analytics.FirebaseAnalyticsManagerImplTest;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class DashboardFirebaseAnalyticsImplTest extends FirebaseAnalyticsManagerImplTest {
    private DashboardFirebaseAnalyticsImpl mAnalytics;

    @Test
    public void testGetScreenName() throws Exception {
        String screenName = mAnalytics.getScreenName();
        assertThat(screenName).isEqualTo(DashboardFirebaseAnalyticsImpl.SCREEN_NAME);
    }

    @Test
    public void testReportShareTap() throws Exception {
        setScreenName();
        mAnalytics.reportShareTap();
        verifyEvent(DashboardFirebaseAnalyticsImpl.TAP_SHARE);
    }

    @Test
    public void testReportAboutTap() throws Exception {
        setScreenName();
        mAnalytics.reportAboutTap();
        verifyEvent(DashboardFirebaseAnalyticsImpl.TAP_ABOUT);
    }

    @Test
    public void testReportOptionsMenuOpened() throws Exception {
        setScreenName();
        mAnalytics.reportOptionsMenuOpened();
        verifyEvent(DashboardFirebaseAnalyticsImpl.MENU_OPTIONS_OPENED);
    }

    @Test
    public void testReportOptionsMenuClosed() throws Exception {
        setScreenName();
        mAnalytics.reportOptionsMenuClosed();
        verifyEvent(DashboardFirebaseAnalyticsImpl.MENU_OPTIONS_CLOSED);
    }

    @Override
    protected FirebaseAnalyticsManagerImpl createAnalyticsManager() {
        mAnalytics = new DashboardFirebaseAnalyticsImpl(mMockAnalytics, mMockBundleProvider);
        return mAnalytics;
    }
}