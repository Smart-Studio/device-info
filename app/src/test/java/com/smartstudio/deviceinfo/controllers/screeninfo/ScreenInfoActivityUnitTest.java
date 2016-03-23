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

package com.smartstudio.deviceinfo.controllers.screeninfo;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.smartstudio.deviceinfo.BuildConfig;
import com.smartstudio.deviceinfo.R;
import com.smartstudio.deviceinfo.controllers.about.AboutActivity;
import com.smartstudio.deviceinfo.logic.ScreenInfoManager;
import com.smartstudio.deviceinfo.model.ScreenInfo;
import com.smartstudio.deviceinfo.robolectric.CustomRobolectricGradleTestRunner;
import com.smartstudio.deviceinfo.ui.screeninfo.ScreenInfoView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowIntent;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.robolectric.Shadows.shadowOf;

@RunWith(CustomRobolectricGradleTestRunner.class)
@Config(sdk = 21, constants = BuildConfig.class)
public class ScreenInfoActivityUnitTest {

    @Inject
    ScreenInfoView mView;
    @Inject
    ScreenInfoManager mScreenInfoManager;
    @Inject
    ScreenInfo mScreenInfo;

    private ScreenInfoActivityForTest mActivity;

    @Before
    public void setUp() throws Exception {
        mActivity = Robolectric.setupActivity(ScreenInfoActivityForTest.class);
        mActivity.mComponent.inject(this);
    }

    @Test
    public void testOnCreate() throws Exception {
        verify(mView).getLayoutResourceId();
        verify(mScreenInfoManager).getScreenInfo();
        verify(mView).showScreenInfo(mScreenInfo);
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
    public void testOnOptionsItemSelectedAboutClicked() throws Exception {
        mockStatic(AboutActivity.class);
        MenuItem item = mockMenuItem(R.id.about);

        mActivity.onOptionsItemSelected(item);

        ShadowActivity shadowActivity = shadowOf(mActivity);
        Intent startedIntent = shadowActivity.getNextStartedActivity();
        ShadowIntent shadowIntent = shadowOf(startedIntent);
        assertThat(shadowIntent.getComponent().getClassName()).isEqualTo(AboutActivity.class.getName());
    }

    @Test
    public void testOnOptionsItemSelectedNotHandledId() throws Exception {
        MenuItem item = mockMenuItem(-3);

        mActivity.onOptionsItemSelected(item);

        ShadowActivity shadowActivity = shadowOf(mActivity);
        Intent startedIntent = shadowActivity.getNextStartedActivity();
        assertThat(startedIntent).isNull();
    }

    private MenuItem mockMenuItem(int itemId) {
        MenuItem item = mock(MenuItem.class);
        when(item.getItemId()).thenReturn(itemId);

        return item;
    }
}