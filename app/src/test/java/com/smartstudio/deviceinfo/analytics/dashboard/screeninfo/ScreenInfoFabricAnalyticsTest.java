package com.smartstudio.deviceinfo.analytics.dashboard.screeninfo;

import com.smartstudio.deviceinfo.analytics.FabricAnalyticsManager;
import com.smartstudio.deviceinfo.analytics.FabricAnalyticsManagerTest;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

public class ScreenInfoFabricAnalyticsTest extends FabricAnalyticsManagerTest {

    private ScreenInfoFabricAnalytics mAnalytics;

    @Test
    public void testGetScreenName() throws Exception {
        String screenName = mAnalytics.getScreenName();
        assertThat(screenName).isEqualTo(ScreenInfoFabricAnalytics.SCREEN_NAME);
    }

    @Test
    public void testSetupContentViewEvent() throws Exception {
        mAnalytics.setupContentViewEvent(mContentViewEvent);
        String screenName = ScreenInfoFabricAnalytics.SCREEN_NAME;
        verify(mContentViewEvent).putContentName(screenName);
        verify(mContentViewEvent).putContentId(String.valueOf(screenName.hashCode()));
        verify(mContentViewEvent).putContentType(ScreenInfoFabricAnalytics.CONTENT_TYPE);
    }

    @Override
    protected FabricAnalyticsManager createAnalyticsManager() {
        mAnalytics = new ScreenInfoFabricAnalytics(mAnswers, mContentViewEvent);
        return mAnalytics;
    }
}