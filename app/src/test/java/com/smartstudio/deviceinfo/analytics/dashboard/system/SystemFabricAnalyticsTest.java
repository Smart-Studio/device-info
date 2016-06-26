package com.smartstudio.deviceinfo.analytics.dashboard.system;

import com.crashlytics.android.answers.CustomEvent;
import com.smartstudio.deviceinfo.analytics.FabricAnalyticsManager;
import com.smartstudio.deviceinfo.analytics.FabricAnalyticsManagerTest;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

public class SystemFabricAnalyticsTest extends FabricAnalyticsManagerTest {
    private SystemFabricAnalytics mAnalytics;

    @Test
    public void testGetScreenName() throws Exception {
        String screenName = mAnalytics.getScreenName();
        assertThat(screenName).isEqualTo(SystemFirebaseAnalyticsImpl.SCREEN_NAME);
    }

    @Test
    public void testSetupContentViewEvent() throws Exception {
        mAnalytics.setupContentViewEvent(mContentViewEvent);
        String screenName = SystemFabricAnalytics.SCREEN_NAME;
        verify(mContentViewEvent).putContentName(screenName);
        verify(mContentViewEvent).putContentId(String.valueOf(screenName.hashCode()));
        verify(mContentViewEvent).putContentType(SystemFabricAnalytics.CONTENT_TYPE);
    }

    @Test
    public void testShare() throws Exception {
        CustomEvent event = mockCustomEvent();
        mAnalytics.reportShare();
        verifyEvent(event, SystemFabricAnalytics.SHARE);
    }

    @Override
    protected FabricAnalyticsManager createAnalyticsManager() {
        mAnalytics = new SystemFabricAnalytics(mAnswers, mContentViewEvent);
        return mAnalytics;
    }
}