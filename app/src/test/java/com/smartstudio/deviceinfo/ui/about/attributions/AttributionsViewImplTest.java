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

package com.smartstudio.deviceinfo.ui.about.attributions;


import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.smartstudio.deviceinfo.R;
import com.smartstudio.deviceinfo.controllers.ToolbarController;
import com.smartstudio.deviceinfo.controllers.about.attributions.AttributionsController;
import com.smartstudio.deviceinfo.model.Attribution;
import com.smartstudio.deviceinfo.ui.BaseView;
import com.smartstudio.deviceinfo.ui.ActionBarViewImplTest;
import com.smartstudio.deviceinfo.utils.ViewUtils;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.List;

import butterknife.ButterKnife;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.verifyStatic;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ButterKnife.class, ViewUtils.class})
public class AttributionsViewImplTest extends ActionBarViewImplTest {

    @Mock
    private AttributionsController mController;
    @Mock
    private AttributionsAdapter mAdapter;
    @Mock
    private RecyclerView.LayoutManager mLayoutManager;
    @Mock
    private List<Attribution> mAttributions;
    @Mock
    private Context mContext;

    private AttributionsViewImpl mView;

    @Before
    public void setUp() throws Exception {
        mView = new AttributionsViewImpl(mController, mAdapter, mLayoutManager, mContext);
    }

    @Test
    public void testGetLayoutResourceId() throws Exception {
        assertThat(mView.getLayoutResourceId()).isEqualTo(R.layout.activity_attributions);
    }

    @Test
    public void testInit() throws Exception {
        mockViews();
        super.testInit();
        verify(mActionBar).setDisplayHomeAsUpEnabled(true);
        verify(mView.mListAttributions).setLayoutManager(mLayoutManager);
        verify(mView.mListAttributions).setAdapter(mAdapter);
    }

    @Override
    public ToolbarController getBaseController() {
        return mController;
    }

    @Override
    public BaseView getBaseView() {
        return mView;
    }

    @Test
    public void testGetToolbarId() throws Exception {
        assertThat(mView.getToolbarId()).isEqualTo(R.id.toolbar_attributions);
    }

    @Test
    public void testShowAttributions() throws Exception {
        mView.showAttributions(mAttributions);
        verify(mAdapter).showAttributions(mAttributions);
    }

    @Test
    public void testShowNoBrowserErrorAlreadyShown() throws Exception {
        mockStatic(ViewUtils.class);
        mView.showNoBrowserError();

        verifyStatic();
        ViewUtils.showNoBrowserToast(eq(mContext), anyObject());
    }

    private void mockViews() {
        mView.mListAttributions = mock(RecyclerView.class);
    }
}
