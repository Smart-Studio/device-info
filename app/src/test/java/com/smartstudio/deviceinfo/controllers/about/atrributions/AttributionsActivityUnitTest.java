package com.smartstudio.deviceinfo.controllers.about.atrributions;


import android.view.MenuItem;

import com.smartstudio.deviceinfo.BuildConfig;
import com.smartstudio.deviceinfo.analytics.about.attributions.AttributionsAnalytics;
import com.smartstudio.deviceinfo.exceptions.BrowserNotFoundException;
import com.smartstudio.deviceinfo.injection.qualifiers.ForFabric;
import com.smartstudio.deviceinfo.injection.qualifiers.ForGoogle;
import com.smartstudio.deviceinfo.logic.attributions.AttributionsProvider;
import com.smartstudio.deviceinfo.model.Attribution;
import com.smartstudio.deviceinfo.robolectric.CustomRobolectricGradleTestRunner;
import com.smartstudio.deviceinfo.ui.about.attributions.AttributionsView;
import com.smartstudio.deviceinfo.utils.Utils;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.verification.VerificationMode;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.rule.PowerMockRule;
import org.robolectric.Robolectric;
import org.robolectric.annotation.Config;

import javax.inject.Inject;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.doThrow;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.verifyStatic;

@RunWith(CustomRobolectricGradleTestRunner.class)
@Config(sdk = 21, constants = BuildConfig.class)
@PowerMockIgnore({"org.mockito.*", "org.robolectric.*", "android.*"})
@PrepareForTest({Utils.class})
public class AttributionsActivityUnitTest {
    private static final String ATTRIBUTION_LIBRARY = "Dagger 2";
    private static final String ATTRIBUTION_URL = "https://github.com/google/dagger";
    private static final String METHOD_OPEN_URL = "openUrl";

    @Rule
    public PowerMockRule rule = new PowerMockRule();

    @Inject
    AttributionsView mView;
    @Inject
    AttributionsProvider mAttributionsProvider;
    @Inject
    @ForGoogle
    AttributionsAnalytics mAnalytics;
    @Inject
    @ForFabric
    AttributionsAnalytics mFabricAnalytics;


    private AttributionsActivityForTest mActivity;

    @Before
    public void setUp() throws Exception {
        mActivity = Robolectric.setupActivity(AttributionsActivityForTest.class);
        mActivity.mComponent.inject(this);
    }

    @Test
    public void testOnCreate() throws Exception {
        verify(mView).getLayoutResourceId();
        verify(mView).init(mActivity.getWindow().getDecorView());
        verify(mAttributionsProvider).getAttributions();
    }

    @Test
    public void testOnResume() throws Exception {
        verify(mAnalytics).reportScreen();
        verify(mFabricAnalytics).reportScreen();
    }

    @Test
    public void testOnOptionsItemSelectedHome() throws Exception {
        onOptionsMenuSelectedTest(android.R.id.home, times(1));
        verify(mAnalytics).reportActionBarBackTap();
        verify(mFabricAnalytics).reportActionBarBackTap();
    }

    @Test
    public void testOnOptionsItemSelectedNotHandled() throws Exception {
        onOptionsMenuSelectedTest(-3, never());
    }

    private void onOptionsMenuSelectedTest(int itemId, VerificationMode verify) {
        MenuItem item = mock(MenuItem.class);
        when(item.getItemId()).thenReturn(itemId);
        mActivity = spy(mActivity);

        mActivity.onOptionsItemSelected(item);
        verify(mActivity, verify).onBackPressed();
    }

    @Test
    public void testOnAttributionClickedTest() throws Exception {
        mockStatic(Utils.class);

        mActivity.onAttributionClicked(mockAttribution());
        verifyStatic();
        Utils.openUrl(anyObject(), eq(ATTRIBUTION_URL));
        verify(mAnalytics).reportAttributionTap(ATTRIBUTION_LIBRARY);
        verify(mFabricAnalytics).reportAttributionTap(ATTRIBUTION_LIBRARY);
    }

    @Test
    public void testOnAttributionClickedNoBrowserIntentTest() throws Exception {
        mockStatic(Utils.class);
        doThrow(new BrowserNotFoundException()).when(Utils.class, METHOD_OPEN_URL, mActivity, ATTRIBUTION_URL);

        mActivity.onAttributionClicked(mockAttribution());
        verifyStatic();
        Utils.openUrl(anyObject(), eq(ATTRIBUTION_URL));
        verify(mView).showNoBrowserError();
        verify(mAnalytics).reportAttributionTap(ATTRIBUTION_LIBRARY);
        verify(mFabricAnalytics).reportAttributionTap(ATTRIBUTION_LIBRARY);
    }

    private Attribution mockAttribution() {
        Attribution attribution = mock(Attribution.class);
        when(attribution.getLibrary()).thenReturn(ATTRIBUTION_LIBRARY);
        when(attribution.getRepoUrl()).thenReturn(ATTRIBUTION_URL);
        return attribution;
    }
}
