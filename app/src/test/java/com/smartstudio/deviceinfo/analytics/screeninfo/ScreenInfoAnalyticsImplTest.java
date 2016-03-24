package com.smartstudio.deviceinfo.analytics.screeninfo;


import com.smartstudio.deviceinfo.analytics.AnalyticsManagerImpl;
import com.smartstudio.deviceinfo.analytics.AnalyticsManagerImplTest;

import org.junit.Test;
import org.powermock.reflect.Whitebox;

import static org.assertj.core.api.Assertions.assertThat;

public class ScreenInfoAnalyticsImplTest extends AnalyticsManagerImplTest {

    private ScreenInfoAnalyticsImpl mScreenInfoAnalytics;

    @Test
    public void testGetScreenName() throws Exception {
        String screenName = mScreenInfoAnalytics.getScreenName();
        assertThat(screenName).isEqualTo(ScreenInfoAnalyticsImpl.SCREEN_NAME);
    }

    @Test
    public void testReportAboutTap() throws Exception {
        setScreenName();
        mScreenInfoAnalytics.reportAboutTap();
        verifyEvent(ScreenInfoAnalyticsImpl.SCREEN_NAME, ScreenInfoAnalyticsImpl.ABOUT_TAPPED);
    }

    @Test
    public void testReportOptionsMenuOpened() throws Exception {
        setScreenName();
        mScreenInfoAnalytics.reportOptionsMenuOpened();
        verifyEvent(ScreenInfoAnalyticsImpl.SCREEN_NAME, ScreenInfoAnalyticsImpl.MENU_OPTIONS_OPENED);
    }

    @Test
    public void testReportOptionsMenuClosed() throws Exception {
        setScreenName();
        mScreenInfoAnalytics.reportOptionsMenuClosed();
        verifyEvent(ScreenInfoAnalyticsImpl.SCREEN_NAME, ScreenInfoAnalyticsImpl.MENU_OPTIONS_CLOSED);
    }

    @Override
    protected AnalyticsManagerImpl createAnalyticsManager() {
        mScreenInfoAnalytics = new ScreenInfoAnalyticsImpl(mAnalytics, mTracker,
                mScreenViewBuilderProvider, mEventBuilderProvider);
        return mScreenInfoAnalytics;
    }

    private void setScreenName() throws Exception {
        Whitebox.invokeMethod(mScreenInfoAnalytics, "setScreenName");
    }
}
