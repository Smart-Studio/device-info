package com.smartstudio.deviceinfo.analytics.dashboard.system;

import com.smartstudio.deviceinfo.analytics.AnalyticsManagerImpl;
import com.smartstudio.deviceinfo.analytics.AnalyticsManagerImplTest;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SystemAnalyticsImplTest extends AnalyticsManagerImplTest {
    private SystemAnalyticsImpl mAnalytics;

    @Test
    public void testGetScreenName() throws Exception {
        String screenName = mAnalytics.getScreenName();
        assertThat(screenName).isEqualTo(SystemAnalyticsImpl.SCREEN_NAME);
    }

    @Test
    public void testShare() throws Exception {
        setScreenName();
        mAnalytics.reportShare();
        verifyEvent(SystemAnalyticsImpl.SCREEN_NAME, SystemAnalyticsImpl.SHARE);
    }

    @Override
    protected AnalyticsManagerImpl createAnalyticsManager() {
        mAnalytics = new SystemAnalyticsImpl(mTracker, mScreenViewBuilderProvider, mEventBuilderProvider);
        return mAnalytics;
    }
}