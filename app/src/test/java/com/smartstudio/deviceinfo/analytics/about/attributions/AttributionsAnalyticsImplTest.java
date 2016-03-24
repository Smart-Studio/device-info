package com.smartstudio.deviceinfo.analytics.about.attributions;

import com.smartstudio.deviceinfo.analytics.AnalyticsManagerImpl;
import com.smartstudio.deviceinfo.analytics.AnalyticsManagerImplTest;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AttributionsAnalyticsImplTest extends AnalyticsManagerImplTest {
    private static final String LIBRARY = "dagger 2";

    private AttributionsAnalyticsImpl mAttributionsAnalytics;

    @Override
    protected AnalyticsManagerImpl createAnalyticsManager() {
        mAttributionsAnalytics = new AttributionsAnalyticsImpl(mAnalytics, mTracker,
                mScreenViewBuilderProvider, mEventBuilderProvider);
        return mAttributionsAnalytics;
    }

    @Test
    public void testGetScreenName() throws Exception {
        String screenName = mAttributionsAnalytics.getScreenName();
        assertThat(screenName).isEqualTo(AttributionsAnalyticsImpl.SCREEN_NAME);
    }

    @Test
    public void testReportAttributionTap() throws Exception {
        setScreenName();
        mAttributionsAnalytics.reportAttributionTap(LIBRARY);
        verifyEventWithLabel(AttributionsAnalyticsImpl.SCREEN_NAME,
                AttributionsAnalyticsImpl.TAP_ATTRIBUTION, LIBRARY);
    }

    @Test
    public void testReportActionBarBackTap() throws Exception {
        setScreenName();
        mAttributionsAnalytics.reportActionBarBackTap();
        verifyEvent(AttributionsAnalyticsImpl.SCREEN_NAME, AttributionsAnalyticsImpl.TAP_ACTION_BAR_BACK);
    }
}
