/*
 * Copyright 2016 Smart Studio.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.smartstudio.deviceinfo.controllers.about;

import android.content.Intent;
import android.view.MenuItem;

import com.smartstudio.deviceinfo.BuildConfig;
import com.smartstudio.deviceinfo.analytics.about.AboutAnalytics;
import com.smartstudio.deviceinfo.controllers.about.attributions.AttributionsActivity;
import com.smartstudio.deviceinfo.exceptions.BrowserNotFoundException;
import com.smartstudio.deviceinfo.injection.qualifiers.ForFabric;
import com.smartstudio.deviceinfo.injection.qualifiers.ForGoogle;
import com.smartstudio.deviceinfo.robolectric.CustomRobolectricGradleTestRunner;
import com.smartstudio.deviceinfo.ui.about.AboutView;
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
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowIntent;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.doThrow;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;
import static org.robolectric.Shadows.shadowOf;

@RunWith(CustomRobolectricGradleTestRunner.class)
@Config(sdk = 21, constants = BuildConfig.class)
@PowerMockIgnore({"org.mockito.*", "org.robolectric.*", "android.*"})
@PrepareForTest({Utils.class})
public class AboutActivityUnitTest {
    private static final String METHOD_OPEN_URL = "openUrl";

    @Rule
    public PowerMockRule rule = new PowerMockRule();

    @Inject
    AboutView mView;
    @Inject
    @ForGoogle
    AboutAnalytics mAnalytics;
    @Inject
    @ForFabric
    AboutAnalytics mFabricAnalytics;

    private AboutActivityForTest mActivity;

    @Before
    public void setUp() throws Exception {
        mActivity = Robolectric.setupActivity(AboutActivityForTest.class);
        mActivity.mComponent.inject(this);
    }

    @Test
    public void testOnCreate() throws Exception {
        verify(mView).getLayoutResourceId();
        verify(mView).init(mActivity.getWindow().getDecorView());
    }

    @Test
    public void testOnResume() throws Exception {
        verify(mAnalytics).reportScreen();
        verify(mFabricAnalytics).reportScreen();
    }

    @Test
    public void testOnOptionsItemSelectedHome() throws Exception {
        onCreateOptionsItemSelectedTest(android.R.id.home, times(1));
        verify(mAnalytics).reportActionBarBackTap();
        verify(mFabricAnalytics).reportActionBarBackTap();
    }

    @Test
    public void testOnOptionsItemSelectedNotHandled() throws Exception {
        onCreateOptionsItemSelectedTest(-3, never());
    }

    private void onCreateOptionsItemSelectedTest(int itemId, VerificationMode mode) {
        MenuItem item = mock(MenuItem.class);
        when(item.getItemId()).thenReturn(itemId);
        mActivity = spy(mActivity);

        mActivity.onOptionsItemSelected(item);
        verify(mActivity, mode).onBackPressed();
    }

    @Test
    public void testOnOpenSourceClicked() throws Exception {
        mockStatic(Utils.class);
        mActivity.onOpenSourceClicked();
        verify(mView, never()).showNoBrowserError();
    }

    @Test
    public void testOnOpenSourceClickedNoBrowser() throws Exception {
        mockStatic(Utils.class);

        doThrow(new BrowserNotFoundException()).when(Utils.class, METHOD_OPEN_URL, mActivity, BuildConfig.REPOSITORY_URL);
        mActivity.onOpenSourceClicked();
        verify(mView).showNoBrowserError();
    }

    @Test
    public void testOnAttributionsClicked() throws Exception {
        mActivity.onAttributionsClicked();
        ShadowActivity shadowActivity = shadowOf(mActivity);
        Intent startedIntent = shadowActivity.getNextStartedActivity();
        ShadowIntent shadowIntent = shadowOf(startedIntent);
        assertThat(shadowIntent.getComponent().getClassName()).isEqualTo(AttributionsActivity.class.getName());
        verify(mAnalytics).reportAttributionsTap();
        verify(mFabricAnalytics).reportAttributionsTap();
    }
}
