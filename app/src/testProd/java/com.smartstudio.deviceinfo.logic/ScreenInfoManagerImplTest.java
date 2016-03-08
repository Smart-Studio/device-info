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
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.DimenRes;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.ViewConfiguration;

import com.smartstudio.deviceinfo.model.ScreenInfo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ViewConfiguration.class, Build.class})
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
    private TypedArray mAttrs;

    private ScreenInfoManagerImpl mScreenInfoManager;

    @Before
    public void setUp() throws Exception {
        when(mContext.getResources()).thenReturn(mResources);
        mockStatic(ViewConfiguration.class, Build.class);
        when(ViewConfiguration.get(mContext)).thenReturn(mViewConfiguration);
        when(mResources.getConfiguration()).thenReturn(mConfiguration);
        when(mTheme.obtainStyledAttributes(any())).thenReturn(mAttrs);
        when(mContext.getTheme()).thenReturn(mTheme);

        mockStaticField(Build.class, "MODEL", MODEL);
        mockStaticField(Build.class, "MANUFACTURER", MANUFACTURER);
        mockStaticField(Build.VERSION.class, "RELEASE", ANDROID_VERSION);
        mDisplayMetrics.densityDpi = DENSITY_DPI;
        mDisplayMetrics.density = DENSITY;
        mDisplayMetrics.xdpi = DENSITY_X;
        mDisplayMetrics.ydpi = DENSITY_Y;

        mockStatusBarHeight();

        mScreenInfoManager = new ScreenInfoManagerImpl(mDisplay, mDisplayMetrics, mScreenInfo, mContext);
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
        verifyAndroidApiAndCodename(false);
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
        verifyAndroidApiAndCodename(false);
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
        verifyAndroidApiAndCodename(true);
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
        verifyAndroidApiAndCodename(true);
        verifyCommon(screenInfo);
        verifyContentBottomAndHeight(false);
    }

    private void mockStatusBarHeight() {
        when(mResources.getIdentifier(ScreenInfoManagerImpl.STATUS_BAR_HEIGHT, ScreenInfoManagerImpl.DIMEN, ScreenInfoManagerImpl.ANDROID)).thenReturn(STATUS_BAR_ID);
        when(mResources.getDimensionPixelSize(STATUS_BAR_ID)).thenReturn(STATUS_HEIGHT);
        when(mAttrs.getDimension(0, 0)).thenReturn((float) ACTION_BAR_HEIGHT);
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
        mockStaticField(Build.VERSION.class, "SDK_INT", ANDROID_API_JELLY_BEAN_AND_ABOVE);
        mDisplayMetrics.widthPixels = WIDTH;
        mDisplayMetrics.heightPixels = HEIGHT;
    }

    private void mockPreJellyBeanResolution() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        mockStaticField(Build.VERSION.class, "SDK_INT", ANDROID_API_PRE_JELLY_BEAN);
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

    private void verifyAndroidApiAndCodename(boolean preJellyBean) {
        if (preJellyBean) {
            verify(mScreenInfo).setAndroidApi(ANDROID_API_PRE_JELLY_BEAN);
            verify(mScreenInfo).setAndroidCodename(ScreenInfoManagerImpl.HONEYCOMB_MR1);
        } else {
            verify(mScreenInfo).setAndroidApi(ANDROID_API_JELLY_BEAN_AND_ABOVE);
            verify(mScreenInfo).setAndroidCodename(ScreenInfoManagerImpl.JELLY_BEAN_MR2);
        }
    }

    private void verifyCommon(ScreenInfo screenInfo) {
        assertThat(screenInfo).isEqualTo(mScreenInfo);
        verify(mAttrs).getDimension(0, 0);
        verify(mAttrs).recycle();

        verify(mScreenInfo).setScreenSize(SCREEN_SIZE);
        verify(mScreenInfo).setDeviceModel(MODEL);
        verify(mScreenInfo).setManufacturer(MANUFACTURER);
        verify(mScreenInfo).setAndroidVersion(ANDROID_VERSION);
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


    private void mockStaticField(Class clazz, String field, Object value) throws IllegalAccessException {
        Field f = PowerMockito.field(clazz, field);
        f.set(clazz, value);
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

    @Test
    public void testGetAndroidCodename() throws Exception {
        getAndroidCodename(Build.VERSION_CODES.CUPCAKE, ScreenInfoManagerImpl.CUPCAKE, 1);
        getAndroidCodename(Build.VERSION_CODES.DONUT, ScreenInfoManagerImpl.DONUT, 1);
        getAndroidCodename(Build.VERSION_CODES.ECLAIR, ScreenInfoManagerImpl.ECLAIR, 1);
        getAndroidCodename(Build.VERSION_CODES.ECLAIR_0_1, ScreenInfoManagerImpl.ECLAIR_0_1, 1);
        getAndroidCodename(Build.VERSION_CODES.ECLAIR_MR1, ScreenInfoManagerImpl.ECLAIR_MR1, 1);
        getAndroidCodename(Build.VERSION_CODES.FROYO, ScreenInfoManagerImpl.FROYO, 1);
        getAndroidCodename(Build.VERSION_CODES.GINGERBREAD, ScreenInfoManagerImpl.GINGERBREAD, 1);
        getAndroidCodename(Build.VERSION_CODES.GINGERBREAD_MR1, ScreenInfoManagerImpl.GINGERBREAD_MR1, 1);
        getAndroidCodename(Build.VERSION_CODES.HONEYCOMB, ScreenInfoManagerImpl.HONEYCOMB, 1);
        getAndroidCodename(Build.VERSION_CODES.HONEYCOMB_MR1, ScreenInfoManagerImpl.HONEYCOMB_MR1, 1);
        getAndroidCodename(Build.VERSION_CODES.HONEYCOMB_MR2, ScreenInfoManagerImpl.HONEYCOMB_MR2, 1);
        getAndroidCodename(Build.VERSION_CODES.ICE_CREAM_SANDWICH, ScreenInfoManagerImpl.ICE_CREAM_SANDWICH, 1);
        getAndroidCodename(Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1, ScreenInfoManagerImpl.ICE_CREAM_SANDWICH_MR1, 1);
        getAndroidCodename(Build.VERSION_CODES.JELLY_BEAN, ScreenInfoManagerImpl.JELLY_BEAN, 1);
        getAndroidCodename(Build.VERSION_CODES.JELLY_BEAN_MR1, ScreenInfoManagerImpl.JELLY_BEAN_MR1, 1);
        getAndroidCodename(Build.VERSION_CODES.JELLY_BEAN_MR2, ScreenInfoManagerImpl.JELLY_BEAN_MR2, 1);
        getAndroidCodename(Build.VERSION_CODES.KITKAT, ScreenInfoManagerImpl.KITKAT, 1);
        getAndroidCodename(Build.VERSION_CODES.LOLLIPOP, ScreenInfoManagerImpl.LOLLIPOP, 1);
        getAndroidCodename(Build.VERSION_CODES.LOLLIPOP_MR1, ScreenInfoManagerImpl.LOLLIPOP_MR1, 1);
        getAndroidCodename(Build.VERSION_CODES.M, ScreenInfoManagerImpl.MARSHMALLOW, 1);
        getAndroidCodename(50, ScreenInfoManagerImpl.UNKNOWN, 1);
        getAndroidCodename(0, ScreenInfoManagerImpl.UNKNOWN, 2);
        getAndroidCodename(-20, ScreenInfoManagerImpl.UNKNOWN, 3);

    }

    private void getAndroidCodename(int sdkInt, String expectedCodename, int times) throws IllegalAccessException {
        mockStaticField(Build.VERSION.class, "SDK_INT", sdkInt);
        mScreenInfoManager.getScreenInfo();
        verify(mScreenInfo).setAndroidApi(sdkInt);
        verify(mScreenInfo, times(times)).setAndroidCodename(expectedCodename);
    }

}