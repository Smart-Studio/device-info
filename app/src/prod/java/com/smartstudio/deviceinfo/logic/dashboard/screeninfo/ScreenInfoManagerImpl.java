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

package com.smartstudio.deviceinfo.logic.dashboard.screeninfo;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.support.annotation.VisibleForTesting;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.ViewConfiguration;

import com.smartstudio.deviceinfo.R;
import com.smartstudio.deviceinfo.injection.qualifiers.ForActivity;
import com.smartstudio.deviceinfo.model.ScreenInfo;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.inject.Inject;

import timber.log.Timber;

public class ScreenInfoManagerImpl implements ScreenInfoManager {
    protected static final String METHOD_GET_RAW_WIDTH = "getRawWidth";
    protected static final String METHOD_GET_RAW_HEIGHT = "getRawHeight";
    protected static final String STATUS_BAR_HEIGHT = "status_bar_height";
    protected static final String NAVIGATION_BAR_HEIGHT = "navigation_bar_height";
    protected static final String DIMEN = "dimen";
    protected static final String ANDROID = "android";

    //Device screen size
    protected static final String SCREEN_SIZE_SMALL = "small";
    protected static final String SCREEN_SIZE_NORMAL = "normal";
    protected static final String SCREEN_SIZE_LARGE = "large";
    protected static final String SCREEN_SIZE_XLARGE = "xlarge";

    //Device screen density
    protected static final String DENSITY_LDPI = "ldpi";
    protected static final String DENSITY_MDPI = "mdpi";
    protected static final String DENSITY_HDPI = "hdpi";
    protected static final String DENSITY_XHDPI = "xhdpi";
    protected static final String DENSITY_XXHDPI = "xxhdpi";
    protected static final String DENSITY_XXXHDPI = "xxxhdpi";
    protected static final String UNKNOWN = "unknown";

    private final Display mDisplay;
    private final DisplayMetrics mDisplayMetrics;
    private final TypedValue mTypedValue;
    private final ScreenInfo mScreenInfo;
    private final Context mContext;
    private final Resources mResources;

    @Inject
    public ScreenInfoManagerImpl(Display display, DisplayMetrics displayMetrics,
                                 TypedValue typedValue, ScreenInfo screenInfo,
                                 @ForActivity Context context) {
        mDisplay = display;
        mDisplayMetrics = displayMetrics;
        mTypedValue = typedValue;
        mScreenInfo = screenInfo;
        mContext = context;
        mResources = context.getResources();
    }

    @Override
    public ScreenInfo getScreenInfo() {
        int width = 0;
        int height = 0;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            mDisplay.getRealMetrics(mDisplayMetrics);
            width = mDisplayMetrics.widthPixels;
            height = mDisplayMetrics.heightPixels;
        } else {
            mDisplay.getMetrics(mDisplayMetrics);
            try {
                width = getWidth();
                height = getHeight();
            } catch (Exception e) {
                Timber.e(e, "Error getting screen resolution");
            }
        }

        int statusBarResourceId = mResources.getIdentifier(STATUS_BAR_HEIGHT, DIMEN, ANDROID);
        int statusBarHeight = mResources.getDimensionPixelSize(statusBarResourceId);
        mScreenInfo.setStatusBarHeight(statusBarHeight);

        int navigationBarHeight = 0;
        if (!ViewConfiguration.get(mContext).hasPermanentMenuKey()) {
            int navigationBarResourceId = mResources.getIdentifier(NAVIGATION_BAR_HEIGHT, DIMEN, ANDROID);
            navigationBarHeight = mResources.getDimensionPixelSize(navigationBarResourceId);
            mScreenInfo.setNavigationBarHeight(navigationBarHeight);
        }

        mScreenInfo.setScreenSize(calculateScreenSize());

        mScreenInfo.setDeviceModel(Build.MODEL);
        mScreenInfo.setManufacturer(Build.MANUFACTURER);
        mScreenInfo.setWidthPixels(width);
        mScreenInfo.setHeightPixels(height);

        double inches = calculateScreenSizeInches(width, height);
        mScreenInfo.setInches(inches);
        mScreenInfo.setDensityDpi(mDisplayMetrics.densityDpi);
        mScreenInfo.setDensity(mDisplayMetrics.density);

        mScreenInfo.setDensityCode(calculateDensityCode());
        mScreenInfo.setDensityX(mDisplayMetrics.xdpi);
        mScreenInfo.setDensityY(mDisplayMetrics.ydpi);

        int actionBarHeight = 0;
        if (mContext.getTheme().resolveAttribute(R.attr.actionBarSize, mTypedValue, true)) {
            actionBarHeight = TypedValue.complexToDimensionPixelSize(mTypedValue.data, mDisplayMetrics);
        }

        mScreenInfo.setActionBarHeight(actionBarHeight);
        int contentTop = statusBarHeight + actionBarHeight;
        mScreenInfo.setContentTop(contentTop);
        mScreenInfo.setContentHeight(height - statusBarHeight - navigationBarHeight);
        mScreenInfo.setContentBottom(height - navigationBarHeight);

        return mScreenInfo;
    }

    private double calculateScreenSizeInches(int width, int height) {
        return Math.sqrt(Math.pow(width / mDisplayMetrics.xdpi, 2) + Math.pow(height / mDisplayMetrics.ydpi, 2));
    }

    private String calculateScreenSize() {
        int screenSize = mResources.getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK;
        switch (screenSize) {
            case Configuration.SCREENLAYOUT_SIZE_SMALL:
                return SCREEN_SIZE_SMALL;
            case Configuration.SCREENLAYOUT_SIZE_NORMAL:
                return SCREEN_SIZE_NORMAL;
            case Configuration.SCREENLAYOUT_SIZE_LARGE:
                return SCREEN_SIZE_LARGE;
            case Configuration.SCREENLAYOUT_SIZE_XLARGE:
                return SCREEN_SIZE_XLARGE;
            default:
                return UNKNOWN;
        }
    }

    private String calculateDensityCode() {
        switch (mDisplayMetrics.densityDpi) {
            case DisplayMetrics.DENSITY_LOW:
                return DENSITY_LDPI;
            case DisplayMetrics.DENSITY_MEDIUM:
                return DENSITY_MDPI;
            case DisplayMetrics.DENSITY_HIGH:
                return DENSITY_HDPI;
            case DisplayMetrics.DENSITY_280:
            case DisplayMetrics.DENSITY_TV:
            case DisplayMetrics.DENSITY_XHIGH:
                return DENSITY_XHDPI;
            case DisplayMetrics.DENSITY_360:
            case DisplayMetrics.DENSITY_400:
            case DisplayMetrics.DENSITY_420:
            case DisplayMetrics.DENSITY_XXHIGH:
                return DENSITY_XXHDPI;
            case DisplayMetrics.DENSITY_560:
            case DisplayMetrics.DENSITY_XXXHIGH:
                return DENSITY_XXXHDPI;
            default:
                return UNKNOWN;
        }
    }

    @VisibleForTesting
    int getWidth() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Method getRawWidthMethod = Display.class.getMethod(METHOD_GET_RAW_WIDTH);
        return (int) getRawWidthMethod.invoke(mDisplay);
    }

    @VisibleForTesting
    int getHeight() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Method getRawHeightMethod = Display.class.getMethod(METHOD_GET_RAW_HEIGHT);
        return (int) getRawHeightMethod.invoke(mDisplay);
    }
}
