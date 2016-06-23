package com.smartstudio.deviceinfo.analytics.about.attributions;

import com.smartstudio.deviceinfo.analytics.FirebaseAnalyticsManagerImpl;
import com.smartstudio.deviceinfo.analytics.FirebaseAnalyticsManagerImplTest;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

public class AttributionsFirebaseAnalyticsImplTest extends FirebaseAnalyticsManagerImplTest {
    private static final String LIBRARY = "dagger 2";

    private AttributionsFirebaseAnalyticsImpl mAttributionsAnalytics;

    @Override
    protected FirebaseAnalyticsManagerImpl createAnalyticsManager() {
        mAttributionsAnalytics = new AttributionsFirebaseAnalyticsImpl(mMockAnalytics, mMockBundleProvider);
        return mAttributionsAnalytics;
    }

    @Test
    public void testGetScreenName() throws Exception {
        String screenName = mAttributionsAnalytics.getScreenName();
        assertThat(screenName).isEqualTo(AttributionsFirebaseAnalyticsImpl.SCREEN_NAME);
    }

    @Test
    public void testReportAttributionTap() throws Exception {
        setScreenName();
        mAttributionsAnalytics.reportAttributionTap(LIBRARY);
        verifyEvent(AttributionsFirebaseAnalyticsImpl.TAP_ATTRIBUTION);
        verify(mMockBundle).putString(AttributionsFirebaseAnalyticsImpl.PARAM_LIBRARY, LIBRARY);
    }

    @Test
    public void testReportActionBarBackTap() throws Exception {
        setScreenName();
        mAttributionsAnalytics.reportActionBarBackTap();
        verifyEvent(AttributionsFirebaseAnalyticsImpl.TAP_ACTION_BAR_BACK);
    }
}
