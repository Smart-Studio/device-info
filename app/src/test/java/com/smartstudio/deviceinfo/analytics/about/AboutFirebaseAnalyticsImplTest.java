package com.smartstudio.deviceinfo.analytics.about;

import com.smartstudio.deviceinfo.analytics.FirebaseAnalyticsManagerImpl;
import com.smartstudio.deviceinfo.analytics.FirebaseAnalyticsManagerImplTest;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AboutFirebaseAnalyticsImplTest extends FirebaseAnalyticsManagerImplTest {
    private AboutFirebaseAnalyticsImpl mAnalytics;

    @Override
    protected FirebaseAnalyticsManagerImpl createAnalyticsManager() {
        mAnalytics = new AboutFirebaseAnalyticsImpl(mMockAnalytics, mMockBundleProvider);
        return mAnalytics;
    }

    @Test
    public void testGetScreenName() throws Exception {
        String screenName = mAnalytics.getScreenName();
        assertThat(screenName).isEqualTo(AboutFirebaseAnalyticsImpl.SCREEN_NAME);
    }

    @Test
    public void testReportOpenSourceTap() throws Exception {
        setScreenName();
        mAnalytics.reportOpenSourceTap();
        verifyEvent(AboutFirebaseAnalyticsImpl.TAP_OPEN_SOURCE);
    }

    @Test
    public void testReportAttributionsTap() throws Exception {
        setScreenName();
        mAnalytics.reportAttributionsTap();
        verifyEvent(AboutFirebaseAnalyticsImpl.TAP_ATTRIBUTIONS);
    }

    @Test
    public void testReportActionBarBackTap() throws Exception {
        setScreenName();
        mAnalytics.reportActionBarBackTap();
        verifyEvent(AboutFirebaseAnalyticsImpl.TAP_ACTION_BAR_BACK);
    }
}
