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
import com.smartstudio.deviceinfo.controllers.about.attributions.AttributionsActivity;
import com.smartstudio.deviceinfo.exceptions.BrowserNotFoundException;
import com.smartstudio.deviceinfo.robolectric.CustomRobolectricGradleTestRunner;
import com.smartstudio.deviceinfo.ui.about.AboutView;
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
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowIntent;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.doThrow;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.robolectric.Shadows.shadowOf;

@RunWith(CustomRobolectricGradleTestRunner.class)
@Config(sdk = 21, constants = BuildConfig.class)
@PowerMockIgnore({"org.mockito.*", "org.robolectric.*", "android.*"})
@PrepareForTest({Utils.class})
public class AboutActivityUnitTest {
    @Rule
    public PowerMockRule rule = new PowerMockRule();

    @Inject
    AboutView mView;

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
    public void testOnOpenSourceClicked() throws Exception {
        mockStatic(Utils.class);

        mActivity.onOpenSourceClicked();
        verify(mView, never()).showNoBrowserError();

        doThrow(new BrowserNotFoundException()).when(Utils.class, "openUrl", mActivity, BuildConfig.REPOSITORY_URL);
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
    }
}
