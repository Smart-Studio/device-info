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
        verifyEvent(AboutAnalyticsImpl.SCREEN_NAME, AboutAnalyticsImpl.OPEN_SOURCE_TAP);
    }

    @Test
    public void testReportAttributionsTap() throws Exception {
        setScreenName();
        mAboutAnalytics.reportAttributionsTap();
        verifyEvent(AboutAnalyticsImpl.SCREEN_NAME, AboutAnalyticsImpl.ATTRIBUTIONS_TAP);
    }

    @Test
    public void testReportActionBarBackTap() throws Exception {
        setScreenName();
        mAboutAnalytics.reportActionBarBackTap();
        verifyEvent(AboutAnalyticsImpl.SCREEN_NAME, AboutAnalyticsImpl.ACTION_BAR_BACK_TAP);
    }

    private void setScreenName() throws Exception {
        Whitebox.invokeMethod(mAboutAnalytics, "setScreenName");
    }
}
