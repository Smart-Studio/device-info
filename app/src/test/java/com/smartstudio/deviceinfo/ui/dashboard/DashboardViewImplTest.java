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

package com.smartstudio.deviceinfo.ui.dashboard;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.smartstudio.deviceinfo.R;
import com.smartstudio.deviceinfo.controllers.ToolbarController;
import com.smartstudio.deviceinfo.controllers.dashboard.DashboardContentController;
import com.smartstudio.deviceinfo.controllers.dashboard.DashboardController;
import com.smartstudio.deviceinfo.ui.ActionBarViewImplTest;
import com.smartstudio.deviceinfo.ui.BaseView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import butterknife.ButterKnife;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ButterKnife.class)
public class DashboardViewImplTest extends ActionBarViewImplTest {

    @Mock
    private DashboardController mMockController;
    @Mock
    private DashboardPagerAdapter mMockAdapter;


    private DashboardViewImpl mView;

    @Before
    public void setUp() throws Exception {
        mView = new DashboardViewImpl(mMockController, mMockAdapter);
        super.setUp();
    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_dashboard;
    }

    @Override
    public BaseView getBaseView() {
        return mView;
    }

    @Test
    public void testGetToolbarId() throws Exception {
        int toolbarId = mView.getToolbarId();
        assertThat(toolbarId).isEqualTo(R.id.toolbar_dashboard);
    }

    @Test
    public void testInit() throws Exception {
        super.testInit();
        verify(mView.mPager).setAdapter(mMockAdapter);
        verify(mView.mTabs).setupWithViewPager(mView.mPager);
    }

    @Test
    public void testShowShareDialog() throws Exception {
        initMocks();
        DashboardContentController mockController = mock(DashboardContentController.class);
        when(mMockAdapter.getItemAt(anyInt())).thenReturn(mockController);

        mView.showShareDialog();

        verify(mMockAdapter).getItemAt(anyInt());
        verify(mockController).onSharedClicked();
    }

    @Override
    protected void initMocks() {
        super.initMocks();
        mView.mTabs = mock(TabLayout.class);
        mView.mPager = mock(ViewPager.class);
    }

    @Override
    public ToolbarController getBaseController() {
        return mMockController;
    }
}