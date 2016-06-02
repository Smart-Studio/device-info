package com.smartstudio.deviceinfo.analytics.about;

import com.smartstudio.deviceinfo.analytics.AnalyticsManagerImpl;
import com.smartstudio.deviceinfo.analytics.AnalyticsManagerImplTest;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AboutAnalyticsImplTest extends AnalyticsManagerImplTest {
    private AboutAnalyticsImpl mAnalytics;

    @Override
    protected AnalyticsManagerImpl createAnalyticsManager() {
        mAnalytics = new AboutAnalyticsImpl(mTracker,
                mScreenViewBuilderProvider, mEventBuilderProvider);
        return mAnalytics;
    }

    @Test
    public void testGetScreenName() throws Exception {
        String screenName = mAnalytics.getScreenName();
        assertThat(screenName).isEqualTo(AboutAnalyticsImpl.SCREEN_NAME);
    }

    @Test
    public void testReportOpenSourceTap() throws Exception {
        setScreenName();
        mAnalytics.reportOpenSourceTap();
        verifyEvent(AboutAnalyticsImpl.SCREEN_NAME, AboutAnalyticsImpl.TAP_OPEN_SOURCE);
    }

    @Test
    public void testReportAttributionsTap() throws Exception {
        setScreenName();
        mAnalytics.reportAttributionsTap();
        verifyEvent(AboutAnalyticsImpl.SCREEN_NAME, AboutAnalyticsImpl.TAP_ATTRIBUTIONS);
    }

    @Test
    public void testReportActionBarBackTap() throws Exception {
        setScreenName();
        mAnalytics.reportActionBarBackTap();
        verifyEvent(AboutAnalyticsImpl.SCREEN_NAME, AboutAnalyticsImpl.TAP_ACTION_BAR_BACK);
    }
}
