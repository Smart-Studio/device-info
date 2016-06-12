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

package com.smartstudio.deviceinfo.controllers.dashboard;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.smartstudio.deviceinfo.BuildConfig;
import com.smartstudio.deviceinfo.R;
import com.smartstudio.deviceinfo.analytics.dashboard.DashboardAnalytics;
import com.smartstudio.deviceinfo.controllers.about.AboutActivity;
import com.smartstudio.deviceinfo.injection.qualifiers.ForFabric;
import com.smartstudio.deviceinfo.injection.qualifiers.ForGoogle;
import com.smartstudio.deviceinfo.robolectric.CustomRobolectricGradleTestRunner;
import com.smartstudio.deviceinfo.ui.dashboard.DashboardView;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.modules.junit4.rule.PowerMockRule;
import org.robolectric.Robolectric;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowIntent;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.doReturn;
import static org.powermock.api.mockito.PowerMockito.when;
import static org.robolectric.Shadows.shadowOf;

@RunWith(CustomRobolectricGradleTestRunner.class)
@Config(sdk = 21, constants = BuildConfig.class)
@PowerMockIgnore({"org.mockito.*", "org.robolectric.*", "android.*"})
public class DashboardActivityUnitTest {
    @Rule
    public PowerMockRule rule = new PowerMockRule();
    @Inject
    DashboardView mView;
    @Inject
    @ForGoogle
    DashboardAnalytics mAnalytics;
    @Inject
    @ForFabric
    DashboardAnalytics mFabricAnalytics;

    private DashboardActivityForTest mActivity;

    @Before
    public void setUp() throws Exception {
        mActivity = Robolectric.setupActivity(DashboardActivityForTest.class);
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
    public void testSetUpToolbar() throws Exception {
        mActivity = spy(mActivity);
        Toolbar toolbar = mock(Toolbar.class);
        when(toolbar.getContext()).thenReturn(mActivity);

        mActivity.setUpToolbar(toolbar);
        verify(mActivity).setSupportActionBar(toolbar);
    }

    @Test
    public void testOnCreateOptionsMenu() throws Exception {
        Menu menu = mock(Menu.class);
        mActivity = spy(mActivity);
        MenuInflater menuInflater = mock(MenuInflater.class);
        doReturn(menuInflater).when(mActivity).getMenuInflater();

        boolean result = mActivity.onCreateOptionsMenu(menu);
        verify(mActivity).getMenuInflater();
        verify(menuInflater).inflate(R.menu.menu_settings, menu);

        assertThat(result).isTrue();
    }

    @Test
    public void testOnOptionsItemSelectedShareClicked() throws Exception {
        MenuItem item = mockMenuItem(R.id.share);

        mActivity.onOptionsItemSelected(item);

        verify(mView).showShareDialog();
        verify(mAnalytics).reportShareTap();
        verify(mFabricAnalytics).reportShareTap();
    }

    @Test
    public void testOnOptionsItemSelectedAboutClicked() throws Exception {
        MenuItem item = mockMenuItem(R.id.about);

        mActivity.onOptionsItemSelected(item);

        ShadowActivity shadowActivity = shadowOf(mActivity);
        Intent startedIntent = shadowActivity.getNextStartedActivity();
        ShadowIntent shadowIntent = shadowOf(startedIntent);
        assertThat(shadowIntent.getComponent().getClassName()).isEqualTo(AboutActivity.class.getName());
        verify(mAnalytics).reportAboutTap();
        verify(mFabricAnalytics).reportAboutTap();
    }

    @Test
    public void testOnOptionsItemSelectedNotHandledId() throws Exception {
        MenuItem item = mockMenuItem(-3);

        mActivity.onOptionsItemSelected(item);

        ShadowActivity shadowActivity = shadowOf(mActivity);
        Intent startedIntent = shadowActivity.getNextStartedActivity();
        assertThat(startedIntent).isNull();
        verify(mAnalytics, never()).reportAboutTap();
        verify(mFabricAnalytics, never()).reportAboutTap();
    }

    private MenuItem mockMenuItem(int itemId) {
        MenuItem item = mock(MenuItem.class);
        when(item.getItemId()).thenReturn(itemId);

        return item;
    }

    @Test
    public void testOnMenuVisibilityChangedVisible() throws Exception {
        mActivity.onMenuVisibilityChanged(true);
        verify(mAnalytics).reportOptionsMenuOpened();
        verify(mFabricAnalytics).reportOptionsMenuOpened();
    }

    @Test
    public void testOnMenuVisibilityChangedNotVisible() throws Exception {
        mActivity.onMenuVisibilityChanged(false);
        verify(mAnalytics).reportOptionsMenuClosed();
        verify(mFabricAnalytics).reportOptionsMenuClosed();
    }
}
