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

import com.smartstudio.deviceinfo.BuildConfig;
import com.smartstudio.deviceinfo.analytics.dashboard.screeninfo.ScreenInfoAnalytics;
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
import org.robolectric.shadows.support.v4.SupportFragmentTestUtil;

import javax.inject.Inject;

import static org.mockito.Mockito.verify;

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

    @Before
    public void setUp() throws Exception {
        ScreenInfoFragmentForTest fragment = new ScreenInfoFragmentForTest();
        SupportFragmentTestUtil.startFragment(fragment);
        fragment.mComponent.inject(this);
    }

    @Test
    public void testOnCreateView() throws Exception {
        verify(mView).getLayoutResourceId();
    }

    @Test
    public void testOnViewCreated() throws Exception {
        verify(mScreenInfoManager).getScreenInfo();
        verify(mView).showScreenInfo(mScreenInfo);
    }

    @Test
    public void testOnResume() throws Exception {
        verify(mAnalytics).reportScreen();
        verify(mFabricAnalytics).reportScreen();
    }
}