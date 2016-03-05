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

import android.support.v7.widget.Toolbar;

import com.smartstudio.deviceinfo.BuildConfig;
import com.smartstudio.deviceinfo.logic.ScreenInfoManager;
import com.smartstudio.deviceinfo.model.ScreenInfo;
import com.smartstudio.deviceinfo.robolectric.CustomRobolectricGradleTestRunner;
import com.smartstudio.deviceinfo.ui.screeninfo.ScreenInfoView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.annotation.Config;

import javax.inject.Inject;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
}