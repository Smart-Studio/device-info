package com.smartstudio.deviceinfo.analytics;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import java.util.Map;

import javax.inject.Provider;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({HitBuilders.class, GoogleAnalytics.class})
public abstract class AnalyticsManagerImplTest {
    private static final String ACTION = "tap_day";
    private static final String LABEL = "label";

    @Mock
    protected GoogleAnalytics mAnalytics;
    @Mock
    protected Tracker mTracker;
    @Mock
    protected Provider<HitBuilders.ScreenViewBuilder> mScreenViewBuilderProvider;
    @Mock
    protected HitBuilders.ScreenViewBuilder mScreenViewBuilder;
    @Mock
    protected Provider<HitBuilders.EventBuilder> mEventBuilderProvider;
    @Mock
    protected HitBuilders.EventBuilder mEventBuilder;
    @Mock
    protected Map<String, String> mScreenViewMap;
    @Mock
    protected Map<String, String> mEventMap;

    protected AnalyticsManagerImpl mAnalyticsManager;

    @Before
    public void setUp() throws Exception {
        when(mScreenViewBuilder.build()).thenReturn(mScreenViewMap);
        when(mScreenViewBuilderProvider.get()).thenReturn(mScreenViewBuilder);
        when(mEventBuilder.setCategory(any())).thenReturn(mEventBuilder);
        when(mEventBuilder.setAction(any())).thenReturn(mEventBuilder);
        when(mEventBuilder.build()).thenReturn(mEventMap);
        when(mEventBuilderProvider.get()).thenReturn(mEventBuilder);
        mAnalyticsManager = createAnalyticsManager();
    }

    @Test
    public void testReportScreen() throws Exception {
        mAnalyticsManager.reportScreen();
        verifyScreenName();
        verify(mTracker).send(mScreenViewMap);
    }

    @Test
    public void testSetScreenName() throws Exception {
        mAnalyticsManager.setScreenName();
        verifyScreenName();
    }

    @Test
    public void testReportEvent() throws Exception {
        mAnalyticsManager.setScreenName();
        mAnalyticsManager.reportEvent(ACTION);
        verifyEvent();
    }

    @Test
    public void testReportEventWithLabel() throws Exception {
        mAnalyticsManager.setScreenName();
        mAnalyticsManager.reportEvent(ACTION, LABEL);
        verifyEvent();
        verify(mEventBuilder).setLabel(LABEL);
    }

    protected abstract AnalyticsManagerImpl createAnalyticsManager();

    protected void verifyEvent(String category, String action) {
        verify(mEventBuilder).setCategory(category);
        verify(mEventBuilder).setAction(action);
        verify(mTracker).send(mEventMap);
    }

    protected void verifyEventWithLabel(String category, String action, String label) {
        verifyEvent(category, action);
        verify(mEventBuilder).setLabel(label);
    }

    protected void setScreenName() throws Exception {
        Whitebox.invokeMethod(mAnalyticsManager, "setScreenName");
    }

    private void verifyScreenName() {
        verify(mTracker).setScreenName(mAnalyticsManager.getScreenName());
    }

    private void verifyEvent() {
        verify(mEventBuilder).setCategory(mAnalyticsManager.getScreenName());
        verify(mEventBuilder).setAction(ACTION);
        verify(mTracker).send(mEventMap);
    }
}