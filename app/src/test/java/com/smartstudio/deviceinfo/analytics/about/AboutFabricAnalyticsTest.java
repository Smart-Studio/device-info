package com.smartstudio.deviceinfo.analytics.about;

import com.crashlytics.android.answers.CustomEvent;
import com.smartstudio.deviceinfo.analytics.FabricAnalyticsManager;
import com.smartstudio.deviceinfo.analytics.FabricAnalyticsManagerTest;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

public class AboutFabricAnalyticsTest extends FabricAnalyticsManagerTest {

    private AboutFabricAnalytics mAnalytics;

    @Test
    public void testGetScreenName() throws Exception {
        String screenName = mAnalytics.getScreenName();
        assertThat(screenName).isEqualTo(AboutFabricAnalytics.SCREEN_NAME);
    }

    @Test
    public void testSetupContentViewEvent() throws Exception {
        mAnalytics.setupContentViewEvent(mContentViewEvent);
        String screenName = AboutFabricAnalytics.SCREEN_NAME;
        verify(mContentViewEvent).putContentName(screenName);
        verify(mContentViewEvent).putContentId(String.valueOf(screenName.hashCode()));
        verify(mContentViewEvent).putContentType(AboutFabricAnalytics.CONTENT_TYPE);
    }

    @Test
    public void testReportOpenSourceTap() throws Exception {
        CustomEvent event = mockCustomEvent();
        mAnalytics.reportOpenSourceTap();
        verifyEvent(event, AboutFabricAnalytics.TAP_OPEN_SOURCE);
    }

    @Test
    public void testReportAttributionsTap() throws Exception {
        CustomEvent event = mockCustomEvent();
        mAnalytics.reportAttributionsTap();
        verifyEvent(event, AboutFabricAnalytics.TAP_ATTRIBUTIONS);
    }

    @Test
    public void testReportActionBarBackTap() throws Exception {
        CustomEvent event = mockCustomEvent();
        mAnalytics.reportActionBarBackTap();
        verifyEvent(event, AboutFabricAnalytics.TAP_ACTION_BAR_BACK);
    }

    @Override
    protected FabricAnalyticsManager createAnalyticsManager() {
        mAnalytics = new AboutFabricAnalytics(mAnswers, mContentViewEvent);
        return mAnalytics;
    }
}