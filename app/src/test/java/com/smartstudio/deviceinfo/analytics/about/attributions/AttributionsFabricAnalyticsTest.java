package com.smartstudio.deviceinfo.analytics.about.attributions;

import com.crashlytics.android.answers.CustomEvent;
import com.smartstudio.deviceinfo.analytics.FabricAnalyticsManager;
import com.smartstudio.deviceinfo.analytics.FabricAnalyticsManagerTest;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;


public class AttributionsFabricAnalyticsTest extends FabricAnalyticsManagerTest {
    private static final String LIBRARY = "dagger 2";

    private AttributionsFabricAnalytics mAnalytics;

    @Test
    public void testGetScreenName() throws Exception {
        String screenName = mAnalytics.getScreenName();
        assertThat(screenName).isEqualTo(AttributionsFabricAnalytics.SCREEN_NAME);
    }

    @Test
    public void testSetupContentViewEvent() throws Exception {
        mAnalytics.setupContentViewEvent(mContentViewEvent);
        String screenName = AttributionsFabricAnalytics.SCREEN_NAME;
        verify(mContentViewEvent).putContentName(screenName);
        verify(mContentViewEvent).putContentId(String.valueOf(screenName.hashCode()));
        verify(mContentViewEvent).putContentType(AttributionsFabricAnalytics.CONTENT_TYPE);
    }

    @Test
    public void testReportAttributionTap() throws Exception {
        CustomEvent event = mockCustomEvent();
        mAnalytics.reportAttributionTap(LIBRARY);
        verifyEvent(event, AttributionsFabricAnalytics.TAP_ATTRIBUTION);
        verify(event).putCustomAttribute(AttributionsFabricAnalytics.ATTRIBUTION_LIBRARY, LIBRARY);
    }

    @Test
    public void testReportActionBarBackTap() throws Exception {
        CustomEvent event = mockCustomEvent();
        mAnalytics.reportActionBarBackTap();
        verifyEvent(event, AttributionsFabricAnalytics.TAP_ACTION_BAR_BACK);
    }

    @Override
    protected FabricAnalyticsManager createAnalyticsManager() {
        mAnalytics = new AttributionsFabricAnalytics(mAnswers, mContentViewEvent);
        return mAnalytics;
    }
}