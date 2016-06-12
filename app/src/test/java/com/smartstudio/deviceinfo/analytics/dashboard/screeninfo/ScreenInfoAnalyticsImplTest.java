package com.smartstudio.deviceinfo.analytics.dashboard.screeninfo;


import com.smartstudio.deviceinfo.analytics.AnalyticsManagerImpl;
import com.smartstudio.deviceinfo.analytics.AnalyticsManagerImplTest;
import com.smartstudio.deviceinfo.analytics.dashboard.system.SystemAnalyticsImpl;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ScreenInfoAnalyticsImplTest extends AnalyticsManagerImplTest {

    private ScreenInfoAnalyticsImpl mAnalytics;

    @Test
    public void testGetScreenName() throws Exception {
        String screenName = mAnalytics.getScreenName();
        assertThat(screenName).isEqualTo(ScreenInfoAnalyticsImpl.SCREEN_NAME);
    }

    @Test
    public void testShare() throws Exception {
        setScreenName();
        mAnalytics.reportShare();
        verifyEvent(ScreenInfoAnalyticsImpl.SCREEN_NAME, ScreenInfoAnalyticsImpl.SHARE);
    }

    @Override
    protected AnalyticsManagerImpl createAnalyticsManager() {
        mAnalytics = new ScreenInfoAnalyticsImpl(mTracker, mScreenViewBuilderProvider, mEventBuilderProvider);
        return mAnalytics;
    }
}
