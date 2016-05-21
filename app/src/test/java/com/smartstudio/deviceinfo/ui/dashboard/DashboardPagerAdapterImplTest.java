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
import android.support.v4.app.FragmentManager;

import com.smartstudio.deviceinfo.R;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
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
    public void testGetItem() throws Exception {
        mAdapter.getItem(DashboardPagerAdapterImpl.POS_SCREEN_TAB);
    }

    @Test(expected = IllegalStateException.class)
    public void testGetItemNoValid() throws Exception {
        mAdapter.getItem(POS_INVALID);
    }

    @Test
    public void testGetPageTitle() throws Exception {
        when(mMockResources.getString(R.string.tab_screen_info)).thenReturn(TITLE_SCREEN);

        CharSequence title = mAdapter.getPageTitle(DashboardPagerAdapterImpl.POS_SCREEN_TAB);

        verify(mMockResources).getString(R.string.tab_screen_info);
        assertThat(title).isEqualTo(TITLE_SCREEN);
    }

    @Test
    public void testGetCount() throws Exception {
        assertThat(mAdapter.getCount()).isEqualTo(DashboardPagerAdapterImpl.PAGE_COUNT);
    }
}