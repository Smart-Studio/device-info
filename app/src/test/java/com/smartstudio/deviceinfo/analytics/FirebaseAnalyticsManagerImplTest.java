package com.smartstudio.deviceinfo.analytics;

import android.os.Bundle;

import com.google.firebase.analytics.FirebaseAnalytics;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import javax.inject.Provider;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({FirebaseAnalytics.class, Bundle.class})
public abstract class FirebaseAnalyticsManagerImplTest {
    private static final String EVENT = "tap_day";
    private static final String PARAM_LABEL = "label";
    private static final String LABEL = "label value";

    @Mock
    protected FirebaseAnalytics mMockAnalytics;
    @Mock
    protected Provider<Bundle> mMockBundleProvider;
    @Mock
    protected Bundle mMockBundle;


    protected FirebaseAnalyticsManagerImpl mAnalyticsManager;

    @Before
    public void setUp() throws Exception {
        when(mMockBundleProvider.get()).thenReturn(mMockBundle);
        mAnalyticsManager = createAnalyticsManager();
    }

    @Test
    public void testReportScreen() throws Exception {
        mAnalyticsManager.setScreenName();
        mAnalyticsManager.reportScreen();
        verify(mMockAnalytics).logEvent(FirebaseAnalyticsManagerImpl.EVENT_VIEW, mMockBundle);
    }

    @Test
    public void testReportEvent() throws Exception {
        mAnalyticsManager.setScreenName();
        mAnalyticsManager.reportEvent(EVENT);
        verifyEvent();
    }

    @Test
    public void testReportEventWithParams() throws Exception {
        mAnalyticsManager.setScreenName();
        mMockBundle.putString(FirebaseAnalyticsManagerImpl.PARAM_SCREEN, getScreenName());
        mMockBundle.putString(PARAM_LABEL, LABEL);
        mAnalyticsManager.reportEvent(EVENT, mMockBundle);
        verify(mMockBundle).putString(FirebaseAnalyticsManagerImpl.PARAM_SCREEN, getScreenName());
        verify(mMockBundle).putString(PARAM_LABEL, LABEL);
    }

    protected abstract FirebaseAnalyticsManagerImpl createAnalyticsManager();

    protected void verifyEvent(String event) {
        verify(mMockBundleProvider).get();
        verify(mMockBundle).putString(FirebaseAnalyticsManagerImpl.PARAM_SCREEN, getScreenName());
        verify(mMockAnalytics).logEvent(event, mMockBundle);
    }

    private void verifyEvent() {
        verifyEvent(EVENT);
    }

    private String getScreenName() {
        return Whitebox.getInternalState(mAnalyticsManager, "mScreenName");
    }

    protected void setScreenName() {
        mAnalyticsManager.setScreenName();
    }
}