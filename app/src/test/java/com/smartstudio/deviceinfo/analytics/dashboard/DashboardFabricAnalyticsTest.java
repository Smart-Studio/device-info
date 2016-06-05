package com.smartstudio.deviceinfo.analytics.dashboard;

import com.crashlytics.android.answers.CustomEvent;
import com.smartstudio.deviceinfo.analytics.FabricAnalyticsManager;
import com.smartstudio.deviceinfo.analytics.FabricAnalyticsManagerTest;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

public class DashboardFabricAnalyticsTest extends FabricAnalyticsManagerTest {
    private DashboardFabricAnalytics mAnalytics;

    @Test
    public void testGetScreenName() throws Exception {
        String screenName = mAnalytics.getScreenName();
        assertThat(screenName).isEqualTo(DashboardFabricAnalytics.SCREEN_NAME);
    }

    @Test
    public void testSetupContentViewEvent() throws Exception {
        mAnalytics.setupContentViewEvent(mContentViewEvent);
        String screenName = DashboardFabricAnalytics.SCREEN_NAME;
        verify(mContentViewEvent).putContentName(screenName);
        verify(mContentViewEvent).putContentId(String.valueOf(screenName.hashCode()));
        verify(mContentViewEvent).putContentType(DashboardFabricAnalytics.CONTENT_TYPE);
    }

    @Test
    public void testReportShareTap() throws Exception {
        CustomEvent event = mockCustomEvent();
        mAnalytics.reportShareTap();
        verifyEvent(event, DashboardFabricAnalytics.TAP_SHARE);
    }

    @Test
    public void testReportAboutTap() throws Exception {
        CustomEvent event = mockCustomEvent();
        mAnalytics.reportAboutTap();
        verifyEvent(event, DashboardFabricAnalytics.TAP_ABOUT);
    }

    @Test
    public void testReportOptionsMenuOpened() throws Exception {
        CustomEvent event = mockCustomEvent();
        mAnalytics.reportOptionsMenuOpened();
        verifyEvent(event, DashboardFabricAnalytics.MENU_OPTIONS_OPENED);
    }

    @Test
    public void testReportOptionsMenuClosed() throws Exception {
        CustomEvent event = mockCustomEvent();
        mAnalytics.reportOptionsMenuClosed();
        verifyEvent(event, DashboardFabricAnalytics.MENU_OPTIONS_CLOSED);
    }

    @Override
    protected FabricAnalyticsManager createAnalyticsManager() {
        mAnalytics = new DashboardFabricAnalytics(mAnswers, mContentViewEvent);
        return mAnalytics;
    }
}