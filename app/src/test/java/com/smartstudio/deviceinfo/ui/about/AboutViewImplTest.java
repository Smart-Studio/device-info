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
import android.content.res.Resources;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.smartstudio.deviceinfo.R;
import com.smartstudio.deviceinfo.controllers.about.AboutController;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import butterknife.ButterKnife;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.verifyStatic;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ButterKnife.class, Toast.class})
public class AboutViewImplTest {
    private static final String VERSION = "v1.0.0";
    private static final String NO_BROWSER_ERROR = "No browser!";

    @Mock
    AboutController mController;
    @Mock
    Context mContext;
    @Mock
    Resources mResources;

    private AboutViewImpl mView;

    @Before
    public void setUp() throws Exception {
        when(mContext.getResources()).thenReturn(mResources);
        mView = new AboutViewImpl(mController, mContext);
    }

    @Test
    public void testGetLayoutResourceId() throws Exception {
        int layoutId = mView.getLayoutResourceId();
        assertThat(layoutId).isEqualTo(R.layout.activity_about);
    }

    @Test
    public void testInit() throws Exception {
        mockStatic(ButterKnife.class);
        View view = mock(View.class);
        when(mResources.getString(eq(R.string.about_version), anyString())).thenReturn(VERSION);
        mockViews();
        ActionBar actionBar = mock(ActionBar.class);
        when(mController.setUpToolbar(anyObject())).thenReturn(actionBar);

        mView.init(view);
        verify(actionBar).setDisplayHomeAsUpEnabled(true);
        verify(mView.mTxtVersion).setText(VERSION);
        verify(mView.mTxtOpenSource).setOnClickListener(anyObject());
        verify(mView.mTxtAttributions).setOnClickListener(anyObject());
    }

    @Test
    public void testGetToolbarId() throws Exception {
        int toolbarId = mView.getToolbarId();
        assertThat(toolbarId).isEqualTo(R.id.toolbar_about);
    }

    @Test
    public void testShowNoBrowserError() throws Exception {
        mockStatic(Toast.class);
        Toast toast = mock(Toast.class);
        when(Toast.makeText(mContext, NO_BROWSER_ERROR, Toast.LENGTH_LONG)).thenReturn(toast);
        when(mResources.getString(R.string.error_no_browser)).thenReturn(NO_BROWSER_ERROR);

        mView.showNoBrowserError();
        verify(mResources).getString(R.string.error_no_browser);
        verifyStatic();
        Toast.makeText(mContext, NO_BROWSER_ERROR, Toast.LENGTH_LONG);
        verify(toast).show();
    }

    @Test
    public void testShowNoBrowserErrorAlreadyShown() throws Exception {
        mockStatic(Toast.class);
        Toast toast = mock(Toast.class);
        View view = mock(View.class);
        when(view.isShown()).thenReturn(true);
        when(toast.getView()).thenReturn(view);
        when(Toast.makeText(mContext, NO_BROWSER_ERROR, Toast.LENGTH_LONG)).thenReturn(toast);
        when(mResources.getString(R.string.error_no_browser)).thenReturn(NO_BROWSER_ERROR);

        mView.showNoBrowserError();
        verify(mResources).getString(R.string.error_no_browser);
        verifyStatic();
        Toast.makeText(mContext, NO_BROWSER_ERROR, Toast.LENGTH_LONG);
        verify(toast).show();

        mView.showNoBrowserError();
        verify(toast).getView();
        verify(view).isShown();
        verify(toast).show();
    }

    @Test
    public void testShowNoBrowserErrorNotShown() throws Exception {
        mockStatic(Toast.class);
        Toast toast = mock(Toast.class);
        View view = mock(View.class);
        when(view.isShown()).thenReturn(false);
        when(toast.getView()).thenReturn(view);
        when(Toast.makeText(mContext, NO_BROWSER_ERROR, Toast.LENGTH_LONG)).thenReturn(toast);
        when(mResources.getString(R.string.error_no_browser)).thenReturn(NO_BROWSER_ERROR);

        mView.showNoBrowserError();
        verify(mResources).getString(R.string.error_no_browser);
        verifyStatic();
        Toast.makeText(mContext, NO_BROWSER_ERROR, Toast.LENGTH_LONG);
        verify(toast).show();

        mView.showNoBrowserError();
        verify(toast).getView();
        verify(view).isShown();
        verifyStatic(times(2));
        Toast.makeText(mContext, NO_BROWSER_ERROR, Toast.LENGTH_LONG);
        verify(toast, times(2)).show();
    }

    private void mockViews() {
        mView.mTxtVersion = mock(TextView.class);
        mView.mTxtOpenSource = mock(TextView.class);
        mView.mTxtAttributions = mock(TextView.class);
    }
}