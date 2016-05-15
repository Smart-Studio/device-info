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

package com.smartstudio.deviceinfo.controllers.dashboard.screeninfo;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.smartstudio.deviceinfo.BuildConfig;
import com.smartstudio.deviceinfo.R;
import com.smartstudio.deviceinfo.analytics.screeninfo.ScreenInfoAnalytics;
import com.smartstudio.deviceinfo.controllers.about.AboutActivity;
import com.smartstudio.deviceinfo.injection.qualifiers.ForFabric;
import com.smartstudio.deviceinfo.injection.qualifiers.ForGoogle;
import com.smartstudio.deviceinfo.logic.ScreenInfoManager;
import com.smartstudio.deviceinfo.model.ScreenInfo;
import com.smartstudio.deviceinfo.robolectric.CustomRobolectricGradleTestRunner;
import com.smartstudio.deviceinfo.ui.screeninfo.ScreenInfoView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowIntent;
import org.robolectric.shadows.support.v4.SupportFragmentTestUtil;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.robolectric.Shadows.shadowOf;

@RunWith(CustomRobolectricGradleTestRunner.class)
@Config(sdk = 21, constants = BuildConfig.class)
public class ScreenInfoFragmentUnitTest {

    @Inject
    ScreenInfoView mView;
    @Inject
    ScreenInfoManager mScreenInfoManager;
    @Inject
    ScreenInfo mScreenInfo;
    @Inject
    @ForGoogle
    ScreenInfoAnalytics mAnalytics;
    @Inject
    @ForFabric
    ScreenInfoAnalytics mFabricAnalytics;

    private ScreenInfoFragmentForTest mFragment;

    @Before
    public void setUp() throws Exception {
        mFragment = new ScreenInfoFragmentForTest();
        SupportFragmentTestUtil.startFragment(mFragment);
        mFragment.mComponent.inject(this);
    }

    @Test
    public void testOnCreateView() throws Exception {


    }
}