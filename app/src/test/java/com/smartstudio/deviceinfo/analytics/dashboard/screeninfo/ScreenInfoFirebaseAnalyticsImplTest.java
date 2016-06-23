package com.smartstudio.deviceinfo.analytics.dashboard.screeninfo;


import com.smartstudio.deviceinfo.analytics.FirebaseAnalyticsManagerImpl;
import com.smartstudio.deviceinfo.analytics.FirebaseAnalyticsManagerImplTest;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ScreenInfoFirebaseAnalyticsImplTest extends FirebaseAnalyticsManagerImplTest {

    private ScreenInfoFirebaseAnalyticsImpl mAnalytics;

    @Test
    public void testGetScreenName() throws Exception {
        String screenName = mAnalytics.getScreenName();
        assertThat(screenName).isEqualTo(ScreenInfoFirebaseAnalyticsImpl.SCREEN_NAME);
    }

    @Test
    public void testShare() throws Exception {
        setScreenName();
        mAnalytics.reportShare();
        verifyEvent(ScreenInfoFirebaseAnalyticsImpl.SHARE);
    }

    @Override
    protected FirebaseAnalyticsManagerImpl createAnalyticsManager() {
        mAnalytics = new ScreenInfoFirebaseAnalyticsImpl(mMockAnalytics, mMockBundleProvider);
        return mAnalytics;
    }
}
