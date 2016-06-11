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

package com.smartstudio.deviceinfo.logic;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.support.annotation.DimenRes;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.ViewConfiguration;

import com.smartstudio.deviceinfo.R;
import com.smartstudio.deviceinfo.logic.dashboard.screeninfo.ScreenInfoManagerImpl;
import com.smartstudio.deviceinfo.model.ScreenInfo;
import com.smartstudio.deviceinfo.utils.TestUtils;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.lang.reflect.InvocationTargetException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.verifyStatic;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ViewConfiguration.class, Build.class, TypedValue.class})
public class ScreenInfoManagerImplTest {
    private static final String MODEL = "Nexus 4";
    private static final String MANUFACTURER = "LGE";
    private static final String ANDROID_VERSION = "4.3";
    private static final int ANDROID_API_JELLY_BEAN_AND_ABOVE = 18;
    private static final int ANDROID_API_PRE_JELLY_BEAN = 12;
    private static final int WIDTH = 768;
    private static final int HEIGHT = 1280;
    private static final String SCREEN_SIZE = "normal";
    private static final int DENSITY_DPI = 320;
    private static final float DENSITY = 2.0f;
    private static final float DENSITY_X = 319.79f;
    private static final float DENSITY_Y = 318.74f;
    private static final int STATUS_HEIGHT = 50;
    private static final int NAVIGATION_BAR_HEIGHT = 96;
    private static final int ACTION_BAR_HEIGHT = 96;
    private static final int CONTENT_TOP = 146;
    private static final int CONTENT_BOTTOM = 1184;
    private static final int CONTENT_BOTTOM_NO_NAV = 1280;
    private static final int CONTENT_HEIGHT = 1134;
    private static final int CONTENT_HEIGHT_NO_NAV = 1230;

    private static final int SCREEN_LAYOUT_SMALL = 1;
    private static final int SCREEN_LAYOUT_NORMAL = 2;
    private static final int SCREEN_LAYOUT_LARGE = 3;
    private static final int SCREEN_LAYOUT_XLARGE = 4;
    @DimenRes
    private static final int STATUS_BAR_ID = 147;
    @DimenRes
    private static final int NAV_BAR_ID = 93;
    private static final double INCHES = 4.679136319266059;

    @Mock
    private Display mDisplay;
    @Mock
    private DisplayMetrics mDisplayMetrics;
    @Mock
    private ScreenInfo mScreenInfo;
    @Mock
    private Context mContext;
    @Mock
    private Resources mResources;
    @Mock
    private ViewConfiguration mViewConfiguration;
    @Mock
    private Configuration mConfiguration;
    @Mock
    private Resources.Theme mTheme;
    @Mock
    private TypedValue mTypedValue;

    private ScreenInfoManagerImpl mScreenInfoManager;

    @Before
    public void setUp() throws Exception {
        when(mContext.getResources()).thenReturn(mResources);
        mockStatic(ViewConfiguration.class, Build.class, TypedValue.class);
        when(ViewConfiguration.get(mContext)).thenReturn(mViewConfiguration);
        when(mResources.getConfiguration()).thenReturn(mConfiguration);
        when(mContext.getTheme()).thenReturn(mTheme);
        when(mTheme.resolveAttribute(R.attr.actionBarSize, mTypedValue, true)).thenReturn(true);
        when(TypedValue.complexToDimensionPixelSize(mTypedValue.data, mDisplayMetrics)).thenReturn(ACTION_BAR_HEIGHT);

        TestUtils.mockStaticField(Build.class, "MODEL", MODEL);
        TestUtils.mockStaticField(Build.class, "MANUFACTURER", MANUFACTURER);
        TestUtils.mockStaticField(Build.VERSION.class, "RELEASE", ANDROID_VERSION);
        mDisplayMetrics.densityDpi = DENSITY_DPI;
        mDisplayMetrics.density = DENSITY;
        mDisplayMetrics.xdpi = DENSITY_X;
        mDisplayMetrics.ydpi = DENSITY_Y;

        mockStatusBarHeight();

        mScreenInfoManager = new ScreenInfoManagerImpl(mDisplay, mDisplayMetrics, mTypedValue, mScreenInfo, mContext);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Test
    public void testGetScreenInfoJellyBeanAndAboveNavigationBar() throws Exception {
        mockHasNavigationBar(true);
        mockJellyBeanAndAboveResolution();
        mockNavigationBarHeight();
        mockScreenLayout();

        ScreenInfo screenInfo = mScreenInfoManager.getScreenInfo();
        verify(mDisplay).getRealMetrics(mDisplayMetrics);
        verifyNavigationBar(true);
        verifyCommon(screenInfo);
        verifyContentBottomAndHeight(true);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Test
    public void testGetScreenInfoJellyBeanAndAboveNoNavigationBar() throws Exception {
        mockHasNavigationBar(false);
        mockJellyBeanAndAboveResolution();
        mockScreenLayout();

        ScreenInfo screenInfo = mScreenInfoManager.getScreenInfo();
        verify(mDisplay).getRealMetrics(mDisplayMetrics);
        verifyStatusBar();
        verifyNavigationBar(false);
        verifyCommon(screenInfo);
        verifyContentBottomAndHeight(false);
    }

    @Test
    public void testGetScreenInfoBelowJellyBeanNavigationBar() throws Exception {
        mockHasNavigationBar(true);
        mockPreJellyBeanResolution();
        mockNavigationBarHeight();
        mockScreenLayout();

        ScreenInfo screenInfo = mScreenInfoManager.getScreenInfo();
        verify(mDisplay).getMetrics(mDisplayMetrics);
        verifyCommon(screenInfo);
        verifyStatusBar();
        verifyNavigationBar(true);
        verifyCommon(screenInfo);
        verifyContentBottomAndHeight(true);
    }

    @Test
    public void testGetScreenInfoBelowJellyBeanNoNavigationBar() throws Exception {
        mockHasNavigationBar(false);
        mockPreJellyBeanResolution();
        mockScreenLayout();

        ScreenInfo screenInfo = mScreenInfoManager.getScreenInfo();
        verify(mDisplay).getMetrics(mDisplayMetrics);
        verifyStatusBar();
        verifyNavigationBar(false);
        verifyCommon(screenInfo);
        verifyContentBottomAndHeight(false);
    }

    private void mockStatusBarHeight() {
        when(mResources.getIdentifier(ScreenInfoManagerImpl.STATUS_BAR_HEIGHT, ScreenInfoManagerImpl.DIMEN, ScreenInfoManagerImpl.ANDROID)).thenReturn(STATUS_BAR_ID);
        when(mResources.getDimensionPixelSize(STATUS_BAR_ID)).thenReturn(STATUS_HEIGHT);
    }

    private void mockHasNavigationBar(boolean enabled) {
        when(mViewConfiguration.hasPermanentMenuKey()).thenReturn(!enabled);
    }

    private void mockNavigationBarHeight() {
        when(mResources.getIdentifier(ScreenInfoManagerImpl.NAVIGATION_BAR_HEIGHT, ScreenInfoManagerImpl.DIMEN, ScreenInfoManagerImpl.ANDROID)).thenReturn(NAV_BAR_ID);
        when(mResources.getDimensionPixelSize(NAV_BAR_ID)).thenReturn(NAVIGATION_BAR_HEIGHT);
    }

    private void mockScreenLayout() {
        mConfiguration.screenLayout = SCREEN_LAYOUT_NORMAL;
    }

    private void mockJellyBeanAndAboveResolution() throws IllegalAccessException {
        TestUtils.mockStaticField(Build.VERSION.class, "SDK_INT", ANDROID_API_JELLY_BEAN_AND_ABOVE);
        mDisplayMetrics.widthPixels = WIDTH;
        mDisplayMetrics.heightPixels = HEIGHT;
    }

    private void mockPreJellyBeanResolution() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        TestUtils.mockStaticField(Build.VERSION.class, "SDK_INT", ANDROID_API_PRE_JELLY_BEAN);
        mScreenInfoManager = spy(mScreenInfoManager);
        doReturn(WIDTH).when(mScreenInfoManager).getWidth();
        doReturn(HEIGHT).when(mScreenInfoManager).getHeight();
    }

    private void verifyStatusBar() {
        verify(mResources).getIdentifier(ScreenInfoManagerImpl.STATUS_BAR_HEIGHT, ScreenInfoManagerImpl.DIMEN, ScreenInfoManagerImpl.ANDROID);
        verify(mResources).getDimensionPixelSize(STATUS_BAR_ID);
    }

    private void verifyNavigationBar(boolean enabled) {
        if (enabled) {
            verify(mResources).getIdentifier(ScreenInfoManagerImpl.NAVIGATION_BAR_HEIGHT, ScreenInfoManagerImpl.DIMEN, ScreenInfoManagerImpl.ANDROID);
            verify(mResources).getDimensionPixelSize(NAV_BAR_ID);
            verify(mScreenInfo).setNavigationBarHeight(NAVIGATION_BAR_HEIGHT);
        } else {
            verify(mResources, never()).getIdentifier(ScreenInfoManagerImpl.NAVIGATION_BAR_HEIGHT, ScreenInfoManagerImpl.DIMEN, ScreenInfoManagerImpl.ANDROID);
            verify(mResources, never()).getDimensionPixelSize(NAV_BAR_ID);
            verify(mScreenInfo, never()).setNavigationBarHeight(anyInt());
        }
    }

    private void verifyCommon(ScreenInfo screenInfo) {
        assertThat(screenInfo).isEqualTo(mScreenInfo);
        verify(mTheme).resolveAttribute(R.attr.actionBarSize, mTypedValue, true);
        verifyStatic();
        TypedValue.complexToDimensionPixelSize(mTypedValue.data, mDisplayMetrics);

        verify(mScreenInfo).setScreenSize(SCREEN_SIZE);
        verify(mScreenInfo).setDeviceModel(MODEL);
        verify(mScreenInfo).setManufacturer(MANUFACTURER);
        verify(mScreenInfo).setWidthPixels(WIDTH);
        verify(mScreenInfo).setHeightPixels(HEIGHT);
        verify(mScreenInfo).setInches(INCHES);
        verify(mScreenInfo).setDensityDpi(DENSITY_DPI);
        verify(mScreenInfo).setDensity(DENSITY);
        verify(mScreenInfo).setActionBarHeight(ACTION_BAR_HEIGHT);
        verify(mScreenInfo).setContentTop(CONTENT_TOP);
    }

    private void verifyContentBottomAndHeight(boolean navigationBar) {
        int bottom = navigationBar ? CONTENT_BOTTOM : CONTENT_BOTTOM_NO_NAV;
        int height = navigationBar ? CONTENT_HEIGHT : CONTENT_HEIGHT_NO_NAV;
        verify(mScreenInfo).setContentBottom(bottom);
        verify(mScreenInfo).setContentHeight(height);
    }

    @Test
    public void testCalculateScreenSize() throws Exception {
        testScreenSize(SCREEN_LAYOUT_SMALL, ScreenInfoManagerImpl.SCREEN_SIZE_SMALL, 1);
        testScreenSize(SCREEN_LAYOUT_NORMAL, ScreenInfoManagerImpl.SCREEN_SIZE_NORMAL, 1);
        testScreenSize(SCREEN_LAYOUT_LARGE, ScreenInfoManagerImpl.SCREEN_SIZE_LARGE, 1);
        testScreenSize(SCREEN_LAYOUT_XLARGE, ScreenInfoManagerImpl.SCREEN_SIZE_XLARGE, 1);
        testScreenSize(0, ScreenInfoManagerImpl.UNKNOWN, 1);
        testScreenSize(-5, ScreenInfoManagerImpl.UNKNOWN, 2);
        testScreenSize(5, ScreenInfoManagerImpl.UNKNOWN, 3);
    }

    private void testScreenSize(int screenLayout, String expectedSize, int times) {
        mConfiguration.screenLayout = screenLayout;
        mScreenInfoManager.getScreenInfo();
        verify(mScreenInfo, times(times)).setScreenSize(expectedSize);

    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Test
    public void testCalculateDensityCode() throws Exception {
        testDensityCode(DisplayMetrics.DENSITY_LOW, ScreenInfoManagerImpl.DENSITY_LDPI, 1);
        testDensityCode(DisplayMetrics.DENSITY_MEDIUM, ScreenInfoManagerImpl.DENSITY_MDPI, 1);
        testDensityCode(DisplayMetrics.DENSITY_HIGH, ScreenInfoManagerImpl.DENSITY_HDPI, 1);
        testDensityCode(DisplayMetrics.DENSITY_XHIGH, ScreenInfoManagerImpl.DENSITY_XHDPI, 1);
        testDensityCode(DisplayMetrics.DENSITY_XXHIGH, ScreenInfoManagerImpl.DENSITY_XXHDPI, 1);
        testDensityCode(DisplayMetrics.DENSITY_XXXHIGH, ScreenInfoManagerImpl.DENSITY_XXXHDPI, 1);
        testDensityCode(-1, ScreenInfoManagerImpl.UNKNOWN, 1);
        testDensityCode(0, ScreenInfoManagerImpl.UNKNOWN, 2);
        testDensityCode(500, ScreenInfoManagerImpl.UNKNOWN, 3);
    }

    private void testDensityCode(int densityDpi, String expectedDensity, int times) {
        mDisplayMetrics.densityDpi = densityDpi;
        mScreenInfoManager.getScreenInfo();
        verify(mScreenInfo, times(times)).setDensityCode(expectedDensity);
    }
}