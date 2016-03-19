package com.smartstudio.deviceinfo.controllers.about.atrributions;


import android.view.MenuItem;

import com.smartstudio.deviceinfo.BuildConfig;
import com.smartstudio.deviceinfo.exceptions.BrowserNotFoundException;
import com.smartstudio.deviceinfo.logic.AttributionsProvider;
import com.smartstudio.deviceinfo.robolectric.CustomRobolectricGradleTestRunner;
import com.smartstudio.deviceinfo.ui.about.attributions.AttributionsView;
import com.smartstudio.deviceinfo.utils.Utils;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
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
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.spy;
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
    private static final String ATTRIBUTION_URL = "https://github.com/google/dagger";

    @Rule
    public PowerMockRule rule = new PowerMockRule();

    @Inject
    AttributionsView mView;
    @Inject
    AttributionsProvider mAttributionsProvider;

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
    public void testOnOptionsItemSelected() throws Exception {
        MenuItem item = mock(MenuItem.class);
        when(item.getItemId()).thenReturn(android.R.id.home, -3);
        mActivity = spy(mActivity);

        mActivity.onOptionsItemSelected(item);
        verify(mActivity).onBackPressed();
        reset(mActivity);

        mActivity.onOptionsItemSelected(item);
        verify(mActivity, never()).onBackPressed();
    }

    @Test
    public void testOnAttributionClickedTest() throws Exception {
        mockStatic(Utils.class);

        mActivity.onAttributionClicked(ATTRIBUTION_URL);
        verifyStatic();
        Utils.openUrl(anyObject(), eq(ATTRIBUTION_URL));
    }

    @Test
    public void testOnAttributionClickedNoBrowserIntentTest() throws Exception {
        mockStatic(Utils.class);
        doThrow(new BrowserNotFoundException()).when(Utils.class, "openUrl", mActivity, ATTRIBUTION_URL);

        mActivity.onAttributionClicked(ATTRIBUTION_URL);
        verifyStatic();
        Utils.openUrl(anyObject(), eq(ATTRIBUTION_URL));
        verify(mView).showNoBrowserError();
    }
}
