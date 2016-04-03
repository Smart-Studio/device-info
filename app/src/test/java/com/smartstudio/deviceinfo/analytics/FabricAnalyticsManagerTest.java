package com.smartstudio.deviceinfo.analytics;

import com.crashlytics.android.answers.Answers;
import com.crashlytics.android.answers.ContentViewEvent;
import com.crashlytics.android.answers.CustomEvent;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.verifyNew;
import static org.powermock.api.mockito.PowerMockito.when;
import static org.powermock.api.mockito.PowerMockito.whenNew;

@RunWith(PowerMockRunner.class)
@PrepareForTest({CustomEvent.class, FabricAnalyticsManager.class})
public abstract class FabricAnalyticsManagerTest {
    private static final String ACTION = "tap_button";

    @Mock
    protected Answers mAnswers;
    @Mock
    protected ContentViewEvent mContentViewEvent;

    protected FabricAnalyticsManager mAnalyticsManager;

    @Before
    public void setUp() throws Exception {
        mAnalyticsManager = createAnalyticsManager();
        when(mContentViewEvent.putContentId(anyString())).thenReturn(mContentViewEvent);
        when(mContentViewEvent.putContentName(anyString())).thenReturn(mContentViewEvent);
        when(mContentViewEvent.putContentType(anyString())).thenReturn(mContentViewEvent);
    }

    @Test
    public void testReportScreen() throws Exception {
        mAnalyticsManager.reportScreen();
        verify(mAnswers).logContentView(mContentViewEvent);
    }

    @Test
    public void testReportEventJustAction() throws Exception {
        CustomEvent event = mockCustomEvent();

        mAnalyticsManager.reportEvent(ACTION);

        verify(event).putCustomAttribute(FabricAnalyticsManager.SCREEN, mAnalyticsManager.mScreenName);
        verify(mAnswers).logCustom(event);
    }

    @Test
    public void testBuildCustomEvent() throws Exception {
        CustomEvent event = mockCustomEvent();

        CustomEvent result = mAnalyticsManager.buildCustomEvent(ACTION);

        verify(event).putCustomAttribute(FabricAnalyticsManager.SCREEN, mAnalyticsManager.mScreenName);
        assertThat(result).isEqualTo(result);
    }

    @Test
    public void testReportEvent() throws Exception {
        CustomEvent event = mockCustomEvent();
        mAnalyticsManager.reportEvent(event);
        verify(mAnswers).logCustom(event);
    }

    protected abstract FabricAnalyticsManager createAnalyticsManager();

    protected CustomEvent mockCustomEvent() throws Exception {
        CustomEvent event = mock(CustomEvent.class);
        when(event.putCustomAttribute(anyString(), anyString())).thenReturn(event);
        whenNew(CustomEvent.class).withArguments(anyString()).thenReturn(event);
        return event;
    }

    protected void verifyEvent(CustomEvent event, String action) throws Exception {
        verifyNew(CustomEvent.class).withArguments(action);
        verify(event).putCustomAttribute(FabricAnalyticsManager.SCREEN, mAnalyticsManager.mScreenName);
        verify(mAnswers).logCustom(event);
    }
}