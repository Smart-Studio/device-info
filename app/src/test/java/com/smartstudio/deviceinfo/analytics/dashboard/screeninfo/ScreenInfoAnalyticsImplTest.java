package com.smartstudio.deviceinfo.analytics.dashboard.screeninfo;


import com.smartstudio.deviceinfo.analytics.AnalyticsManagerImpl;
import com.smartstudio.deviceinfo.analytics.AnalyticsManagerImplTest;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ScreenInfoAnalyticsImplTest extends AnalyticsManagerImplTest {

    private ScreenInfoAnalyticsImpl mAnalytics;

    @Test
    public void testGetScreenName() throws Exception {
        String screenName = mAnalytics.getScreenName();
        assertThat(screenName).isEqualTo(ScreenInfoAnalyticsImpl.SCREEN_NAME);
    }

    @Override
    protected AnalyticsManagerImpl createAnalyticsManager() {
        mAnalytics = new ScreenInfoAnalyticsImpl(mTracker, mScreenViewBuilderProvider, mEventBuilderProvider);
        return mAnalytics;
    }
}
