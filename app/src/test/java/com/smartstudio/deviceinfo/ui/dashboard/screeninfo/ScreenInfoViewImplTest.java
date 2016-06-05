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

package com.smartstudio.deviceinfo.ui.dashboard.screeninfo;

import android.view.View;

import com.smartstudio.deviceinfo.R;
import com.smartstudio.deviceinfo.controllers.dashboard.screeninfo.ScreenInfoController;
import com.smartstudio.deviceinfo.logic.dashboard.screeninfo.ScreenInfoShareManager;
import com.smartstudio.deviceinfo.model.ScreenInfo;
import com.smartstudio.deviceinfo.model.ScreenInfoViewModel;
import com.smartstudio.deviceinfo.ui.BaseView;
import com.smartstudio.deviceinfo.ui.BaseViewImplTest;
import com.smartstudio.deviceinfo.ui.PropertyLayout;
import com.smartstudio.deviceinfo.utils.Utils;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import butterknife.ButterKnife;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ButterKnife.class, Utils.class})
public class ScreenInfoViewImplTest extends BaseViewImplTest {
    private static final String MODEL = "Nexus 4";
    private static final String MANUFACTURER = "LGE";
    private static final int WIDTH = 768;
    private static final int WIDTH_DP = 384;
    private static final int HEIGHT = 1280;
    private static final int HEIGHT_DP = 640;
    private static final double INCHES_DOWN = 4.74;
    private static final double INCHES_ROUND_DOWN = 4.7;
    private static final double INCHES_UP = 4.75;
    private static final double INCHES_ROUND_UP = 4.8;
    private static final String SCREEN_SIZE = "normal";
    private static final int STATUS_HEIGHT = 50;
    private static final int STATUS_HEIGHT_DP = 25;
    private static final int NAVIGATION_BAR_HEIGHT = 96;
    private static final int NAVIGATION_BAR_HEIGHT_DP = 48;
    private static final int DENSITY_DPI = 320;
    private static final double DENSITY_DOWN = 2.03;
    private static final double DENSITY_ROUND_DOWN = 2.0;
    private static final double DENSITY_UP = 2.05;
    private static final double DENSITY_ROUND_UP = 2.1;
    private static final String DENSITY_CODE = "xhdpi";
    private static final double DENSITY_X_DOWN = 319.792;
    private static final double DENSITY_X_ROUND_DOWN = 319.79;
    private static final double DENSITY_X_UP = 319.805;
    private static final double DENSITY_X_ROUND_UP = 319.81;
    private static final double DENSITY_Y_DOWN = 318.741;
    private static final double DENSITY_Y_ROUND_DOWN = 318.74;
    private static final double DENSITY_Y_UP = 318.745;
    private static final double DENSITY_Y_ROUND_UP = 318.75;
    private static final int ACTION_BAR_HEIGHT = 96;
    private static final int ACTION_BAR_HEIGHT_DP = 48;
    private static final int CONTENT_TOP = 146;
    private static final int CONTENT_TOP_DP = 73;
    private static final int CONTENT_BOTTOM = 1184;
    private static final int CONTENT_BOTTOM_DP = 592;
    private static final int CONTENT_HEIGHT = 1134;
    private static final int CONTENT_HEIGHT_DP = 567;

    @Mock
    private ScreenInfoController mMockController;
    @Mock
    private ScreenInfoShareManager mMockShareManager;
    @Mock
    private ScreenInfoViewModel mMockScreenInfo;

    private ScreenInfoViewImpl mView;

    @Before
    public void setUp() throws Exception {
        mView = new ScreenInfoViewImpl(mMockShareManager, mMockScreenInfo);
        super.setUp();
    }

    @Override
    public BaseView getBaseView() {
        return mView;
    }

    @Override
    public int getLayoutResource() {
        return R.layout.fragment_screen_info;
    }

    @Test
    public void testShowScreenInfo() throws Exception {
        mockStatic(Utils.class);
        mockUtils(DENSITY_DOWN);
        mockViews();
        ScreenInfo info = mockScreenInfo(INCHES_DOWN, DENSITY_DOWN, DENSITY_X_DOWN, DENSITY_Y_DOWN);
        mView.showScreenInfo(info);
        verifyViewMocks(INCHES_ROUND_DOWN, DENSITY_ROUND_DOWN, DENSITY_X_ROUND_DOWN, DENSITY_Y_ROUND_DOWN);
    }

    @Test
    public void testShowScreenInfoRoundUp() throws Exception {
        mockStatic(Utils.class);
        mockViews();
        mockUtils(DENSITY_UP);
        ScreenInfo info = mockScreenInfo(INCHES_UP, DENSITY_UP, DENSITY_X_UP, DENSITY_Y_UP);
        mView.showScreenInfo(info);
        verifyViewMocks(INCHES_ROUND_UP, DENSITY_ROUND_UP, DENSITY_X_ROUND_UP, DENSITY_Y_ROUND_UP);
    }

    @Test
    public void testShowScreenInfoNoNavigationBar() throws Exception {
        mockStatic(Utils.class);
        mockUtils(DENSITY_UP);
        mockViews();
        ScreenInfo info = mockScreenInfo(INCHES_UP, DENSITY_UP, DENSITY_X_UP, DENSITY_Y_UP);
        when(info.getNavigationBarHeight()).thenReturn(0);
        mView.showScreenInfo(info);
        verify(mView.mViewScreenNavigation).setVisibility(View.GONE);

    }

    private void mockUtils(double density) {
        when(Utils.pxToDp(WIDTH, density)).thenReturn(WIDTH_DP);
        when(Utils.pxToDp(HEIGHT, density)).thenReturn(HEIGHT_DP);
        when(Utils.pxToDp(STATUS_HEIGHT, density)).thenReturn(STATUS_HEIGHT_DP);
        when(Utils.pxToDp(NAVIGATION_BAR_HEIGHT, density)).thenReturn(NAVIGATION_BAR_HEIGHT_DP);
        when(Utils.pxToDp(CONTENT_HEIGHT, density)).thenReturn(CONTENT_HEIGHT_DP);
        when(Utils.pxToDp(ACTION_BAR_HEIGHT, density)).thenReturn(ACTION_BAR_HEIGHT_DP);
        when(Utils.pxToDp(CONTENT_TOP, density)).thenReturn(CONTENT_TOP_DP);
        when(Utils.pxToDp(CONTENT_BOTTOM, density)).thenReturn(CONTENT_BOTTOM_DP);
    }

    private ScreenInfo mockScreenInfo(double inches, double density, double densityX, double densityY) {
        ScreenInfo info = mock(ScreenInfo.class);
        when(info.getDeviceModel()).thenReturn(MODEL);
        when(info.getManufacturer()).thenReturn(MANUFACTURER);
        when(info.getWidthPixels()).thenReturn(WIDTH);
        when(info.getHeightPixels()).thenReturn(HEIGHT);
        when(info.getInches()).thenReturn(inches);
        when(info.getScreenSize()).thenReturn(SCREEN_SIZE);
        when(info.getStatusBarHeight()).thenReturn(STATUS_HEIGHT);
        when(info.getNavigationBarHeight()).thenReturn(NAVIGATION_BAR_HEIGHT);
        when(info.getDensityDpi()).thenReturn(DENSITY_DPI);
        when(info.getDensity()).thenReturn(density);
        when(info.getDensityCode()).thenReturn(DENSITY_CODE);
        when(info.getDensityX()).thenReturn(densityX);
        when(info.getDensityY()).thenReturn(densityY);
        when(info.getContentHeight()).thenReturn(CONTENT_HEIGHT);
        when(info.getActionBarHeight()).thenReturn(ACTION_BAR_HEIGHT);
        when(info.getContentTop()).thenReturn(CONTENT_TOP);
        when(info.getContentBottom()).thenReturn(CONTENT_BOTTOM);
        return info;
    }

    private void mockViews() {
        mView.mViewDeviceName = mock(PropertyLayout.class);
        mView.mViewManufacturer = mock(PropertyLayout.class);
        mView.mViewScreenRes = mock(PropertyLayout.class);
        mView.mViewScreenStatus = mock(PropertyLayout.class);
        mView.mViewScreenNavigation = mock(PropertyLayout.class);
        mView.mViewScreenSize = mock(PropertyLayout.class);
        mView.mViewScreenInches = mock(PropertyLayout.class);
        mView.mViewScreenDensity = mock(PropertyLayout.class);
        mView.mViewScreenDensityCode = mock(PropertyLayout.class);
        mView.mViewScreenDensityX = mock(PropertyLayout.class);
        mView.mViewScreenDensityY = mock(PropertyLayout.class);
        mView.mViewActionBarHeight = mock(PropertyLayout.class);
        mView.mViewContentTop = mock(PropertyLayout.class);
        mView.mViewContentBottom = mock(PropertyLayout.class);
        mView.mViewContentHeight = mock(PropertyLayout.class);
    }

    private void verifyViewMocks(double inches, double density, double densityX, double densityY) {
        verify(mView.mViewDeviceName).setValue(MODEL);
        verify(mView.mViewManufacturer).setValue(MANUFACTURER);
        verify(mView.mViewScreenRes).setValue(WIDTH + "x" + HEIGHT + " px (" + WIDTH_DP + "x" + HEIGHT_DP + " dp)");
        verify(mView.mViewScreenInches).setValue(inches + "\"");
        verify(mView.mViewScreenSize).setValue(SCREEN_SIZE);
        verify(mView.mViewScreenStatus).setValue(STATUS_HEIGHT + " px (" + STATUS_HEIGHT_DP + " dp)");
        verify(mView.mViewScreenNavigation).setValue(NAVIGATION_BAR_HEIGHT + " px (" + NAVIGATION_BAR_HEIGHT_DP + " dp)");
        verify(mView.mViewScreenDensity).setValue(DENSITY_DPI + " dp (x" + density + ")");
        verify(mView.mViewScreenDensityCode).setValue(DENSITY_CODE);
        verify(mView.mViewScreenDensityX).setValue(densityX + " dp");
        verify(mView.mViewScreenDensityY).setValue(densityY + " dp");
        verify(mView.mViewContentHeight).setValue(CONTENT_HEIGHT + " px (" + CONTENT_HEIGHT_DP + " dp)");
        verify(mView.mViewActionBarHeight).setValue(ACTION_BAR_HEIGHT + " px (" + ACTION_BAR_HEIGHT_DP + " dp)");
        verify(mView.mViewContentTop).setValue(CONTENT_TOP + " px (" + CONTENT_TOP_DP + " dp)");
        verify(mView.mViewContentBottom).setValue(CONTENT_BOTTOM + " px (" + CONTENT_BOTTOM_DP + " dp)");
    }
}