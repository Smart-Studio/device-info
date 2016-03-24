package com.smartstudio.deviceinfo.analytics.about;

import com.smartstudio.deviceinfo.analytics.AnalyticsManagerImpl;
import com.smartstudio.deviceinfo.analytics.AnalyticsManagerImplTest;

import org.junit.Test;
import org.powermock.reflect.Whitebox;

import static org.assertj.core.api.Assertions.assertThat;

public class AboutAnalyticsImplTest extends AnalyticsManagerImplTest {
    private AboutAnalyticsImpl mAboutAnalytics;

    @Override
    protected AnalyticsManagerImpl createAnalyticsManager() {
        mAboutAnalytics = new AboutAnalyticsImpl(mAnalytics, mTracker,
                mScreenViewBuilderProvider, mEventBuilderProvider);
        return mAboutAnalytics;
    }

    @Test
    public void testGetScreenName() throws Exception {
        String screenName = mAboutAnalytics.getScreenName();
        assertThat(screenName).isEqualTo(AboutAnalyticsImpl.SCREEN_NAME);
    }

    @Test
    public void testReportOpenSourceTap() throws Exception {
        setScreenName();
        mAboutAnalytics.reportOpenSourceTap();
        verifyEvent(AboutAnalyticsImpl.SCREEN_NAME, AboutAnalyticsImpl.TAP_OPEN_SOURCE);
    }

    @Test
    public void testReportAttributionsTap() throws Exception {
        setScreenName();
        mAboutAnalytics.reportAttributionsTap();
        verifyEvent(AboutAnalyticsImpl.SCREEN_NAME, AboutAnalyticsImpl.TAP_ATTRIBUTIONS);
    }

    @Test
    public void testReportActionBarBackTap() throws Exception {
        setScreenName();
        mAboutAnalytics.reportActionBarBackTap();
        verifyEvent(AboutAnalyticsImpl.SCREEN_NAME, AboutAnalyticsImpl.TAP_ACTION_BAR_BACK);
    }
}
