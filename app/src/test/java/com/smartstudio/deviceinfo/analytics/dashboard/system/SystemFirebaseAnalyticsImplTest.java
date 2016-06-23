package com.smartstudio.deviceinfo.analytics.dashboard.system;

import com.smartstudio.deviceinfo.analytics.FirebaseAnalyticsManagerImpl;
import com.smartstudio.deviceinfo.analytics.FirebaseAnalyticsManagerImplTest;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SystemFirebaseAnalyticsImplTest extends FirebaseAnalyticsManagerImplTest {
    private SystemFirebaseAnalyticsImpl mAnalytics;

    @Test
    public void testGetScreenName() throws Exception {
        String screenName = mAnalytics.getScreenName();
        assertThat(screenName).isEqualTo(SystemFirebaseAnalyticsImpl.SCREEN_NAME);
    }

    @Test
    public void testShare() throws Exception {
        setScreenName();
        mAnalytics.reportShare();
        verifyEvent(SystemFirebaseAnalyticsImpl.SHARE);
    }

    @Override
    protected FirebaseAnalyticsManagerImpl createAnalyticsManager() {
        mAnalytics = new SystemFirebaseAnalyticsImpl(mMockAnalytics, mMockBundleProvider);
        return mAnalytics;
    }
}