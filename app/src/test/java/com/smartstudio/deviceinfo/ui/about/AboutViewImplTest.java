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

package com.smartstudio.deviceinfo.ui.about;

import android.content.Context;
import android.widget.TextView;

import com.smartstudio.deviceinfo.R;
import com.smartstudio.deviceinfo.controllers.ToolbarController;
import com.smartstudio.deviceinfo.controllers.about.AboutController;
import com.smartstudio.deviceinfo.ui.BaseView;
import com.smartstudio.deviceinfo.ui.ActionBarViewImplTest;
import com.smartstudio.deviceinfo.utils.ViewUtils;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import butterknife.ButterKnife;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.verifyStatic;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ButterKnife.class, ViewUtils.class})
public class AboutViewImplTest extends ActionBarViewImplTest {
    private static final String VERSION = "v1.0.0";

    @Mock
    private AboutController mController;
    @Mock
    private Context mContext;

    private AboutViewImpl mView;

    @Before
    public void setUp() throws Exception {
        mView = new AboutViewImpl(mController, mContext);
    }

    @Test
    public void testGetLayoutResourceId() throws Exception {
        int layoutId = mView.getLayoutResourceId();
        assertThat(layoutId).isEqualTo(R.layout.activity_about);
    }

    @Test
    public void testInit() throws Exception {
        super.testInit();
        verify(mActionBar).setDisplayHomeAsUpEnabled(true);
        verify(mView.mTxtVersion).setText(VERSION);
        verify(mView.mTxtOpenSource).setOnClickListener(anyObject());
        verify(mView.mTxtAttributions).setOnClickListener(anyObject());
    }

    @Override
    public ToolbarController getBaseController() {
        return mController;
    }

    @Override
    public BaseView getBaseView() {
        return mView;
    }

    @Override
    protected void initMocks() {
        super.initMocks();
        when(mContext.getString(eq(R.string.about_version), anyString())).thenReturn(VERSION);
        mockViews();
    }

    @Test
    public void testGetToolbarId() throws Exception {
        int toolbarId = mView.getToolbarId();
        assertThat(toolbarId).isEqualTo(R.id.toolbar_about);
    }

    @Test
    public void testShowNoBrowserError() throws Exception {
        mockStatic(ViewUtils.class);
        mView.showNoBrowserError();

        verifyStatic();
        ViewUtils.showNoBrowserToast(eq(mContext), anyObject());
    }

    private void mockViews() {
        mView.mTxtVersion = mock(TextView.class);
        mView.mTxtOpenSource = mock(TextView.class);
        mView.mTxtAttributions = mock(TextView.class);
    }
}