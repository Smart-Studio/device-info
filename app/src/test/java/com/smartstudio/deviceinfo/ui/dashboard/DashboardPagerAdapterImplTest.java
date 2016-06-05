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

import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.smartstudio.deviceinfo.R;
import com.smartstudio.deviceinfo.controllers.dashboard.DashboardContentController;
import com.smartstudio.deviceinfo.controllers.dashboard.screeninfo.ScreenInfoFragment;
import com.smartstudio.deviceinfo.controllers.dashboard.system.SystemFragment;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(PowerMockRunner.class)
public class DashboardPagerAdapterImplTest {
    private static final String TITLE_SCREEN = "screen";
    private static final int POS_INVALID = 860;

    @Mock
    private FragmentManager mMockFragmentManager;
    @Mock
    private Resources mMockResources;

    private DashboardPagerAdapterImpl mAdapter;

    @Before
    public void setUp() throws Exception {
        mAdapter = new DashboardPagerAdapterImpl(mMockFragmentManager, mMockResources);
    }

    @Test
    public void testGetItemScreenInfo() throws Exception {
        Fragment fragment = mAdapter.getItem(DashboardPagerAdapterImpl.POS_SCREEN_TAB);
        assertThat(fragment).isInstanceOf(ScreenInfoFragment.class);
    }

    @Test
    public void testGetItemSystem() throws Exception {
        Fragment fragment = mAdapter.getItem(DashboardPagerAdapterImpl.POS_SYSTEM_TAB);
        assertThat(fragment).isInstanceOf(SystemFragment.class);
    }

    @Test(expected = IllegalStateException.class)
    public void testGetItemNoValid() throws Exception {
        mAdapter.getItem(POS_INVALID);
    }

    @Test
    public void testGetPageTitleScreenInfo() throws Exception {
        when(mMockResources.getString(R.string.tab_screen_info)).thenReturn(TITLE_SCREEN);

        CharSequence title = mAdapter.getPageTitle(DashboardPagerAdapterImpl.POS_SCREEN_TAB);

        verify(mMockResources).getString(R.string.tab_screen_info);
        assertThat(title).isEqualTo(TITLE_SCREEN);
    }

    @Test
    public void testGetPageTitleSystem() throws Exception {
        when(mMockResources.getString(R.string.tab_system)).thenReturn(TITLE_SCREEN);

        CharSequence title = mAdapter.getPageTitle(DashboardPagerAdapterImpl.POS_SYSTEM_TAB);

        verify(mMockResources).getString(R.string.tab_system);
        assertThat(title).isEqualTo(TITLE_SCREEN);
    }

    @Test
    public void testGetCount() throws Exception {
        assertThat(mAdapter.getCount()).isEqualTo(DashboardPagerAdapterImpl.PAGE_COUNT);
    }

    @Test
    public void testGetItemAt() throws Exception {
        mAdapter = spy(mAdapter);
        DashboardContentController mockContentController = mock(DashboardContentController.class);
        doReturn(mockContentController).when(mAdapter).instantiateItem(eq(null), anyInt());

        mAdapter.getItemAt(anyInt());

        verify(mAdapter).getItemAt(anyInt());
    }
}