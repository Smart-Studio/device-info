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

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.ViewConfiguration;

import com.smartstudio.deviceinfo.injection.qualifiers.ForApplication;
import com.smartstudio.deviceinfo.model.ScreenInfo;

import java.lang.reflect.Method;

import javax.inject.Inject;

import timber.log.Timber;

public class ScreenInfoManagerImpl implements ScreenInfoManager {
    private static final String METHOD_GET_RAW_WIDTH = "getRawWidth";
    private static final String METHOD_GET_RAW_HEIGHT = "getRawHeight";

    //Android versions codenames
    private static final String CUPCAKE = "Cupcake";
    private static final String DONUT = "Donut";
    private static final String ECLAIR = "Eclair";
    private static final String ECLAIR_0_1 = "Eclair 0 1";
    private static final String ECLAIR_MR1 = "Eclair MR1";
    private static final String FROYO = "Froyo";
    private static final String GINGERBREAD = "Gingerbread";
    private static final String GINGERBREAD_MR1 = "Gingerbread MR1";
    private static final String HONEYCOMB = "Honeycomb";
    private static final String HONEYCOMB_MR1 = "Honeycomb MR1";
    private static final String HONEYCOMB_MR2 = "Honeycomb MR2";
    private static final String ICE_CREAM_SANDWICH = "Ice Cream Sandwich";
    private static final String ICE_CREAM_SANDWICH_MR1 = "Ice Cream Sandwich MR1";
    private static final String JELLY_BEAN = "Jelly Bean";
    private static final String JELLY_BEAN_MR1 = "Jelly Bean MR1";
    private static final String JELLY_BEAN_MR2 = "Jelly Bean MR2";
    private static final String KITKAT = "Kitkat";
    private static final String LOLLIPOP = "Lollipop";
    private static final String LOLLIPOP_MR1 = "Lollipop MR1";
    private static final String MARSHMALLOW = "Marshmallow";

    //Device screen size
    private static final String SCREEN_SIZE_SMALL = "small";
    private static final String SCREEN_SIZE_NORMAL = "normal";
    private static final String SCREEN_SIZE_LARGE = "large";
    private static final String SCREEN_SIZE_XLARGE = "xlarge";

    //Device screen density
    private static final String DENSITY_LDPI = "ldpi";
    private static final String DENSITY_MDPI = "mdpi";
    private static final String DENSITY_HDPI = "hdpi";
    private static final String DENSITY_XHDPI = "xhdpi";
    private static final String DENSITY_XXHDPI = "xxhdpi";
    private static final String DENSITY_XXXHDPI = "xxxhdpi";
    private static final String UNKNOWN = "unknown";

    private final Display mDisplay;
    private final DisplayMetrics mDisplayMetrics;
    private final ScreenInfo mScreenInfo;
    private final Context mContext;
    private final Resources mResources;

    @Inject
    public ScreenInfoManagerImpl(Display display, DisplayMetrics displayMetrics,
                                 ScreenInfo screenInfo, @ForApplication Context context,
                                 Resources resources) {
        mDisplay = display;
        mDisplayMetrics = displayMetrics;
        mScreenInfo = screenInfo;
        mContext = context;
        mResources = resources;
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
                Method getRawWidthMethod = Display.class.getMethod(METHOD_GET_RAW_WIDTH);
                Method getRawHeightMethod = Display.class.getMethod(METHOD_GET_RAW_HEIGHT);

                width = (int) getRawWidthMethod.invoke(mDisplay);
                height = (int) getRawHeightMethod.invoke(mDisplay);
            } catch (Exception e) {
                Timber.e(e, "Error getting screen resolution");
            }
        }

        int statusBarResourceId = mResources.getIdentifier("status_bar_height", "dimen", "android");
        int statusBarHeight = mResources.getDimensionPixelSize(statusBarResourceId);
        mScreenInfo.setStatusBarHeight(statusBarHeight);

        int navigationBarHeight = 0;
        if (!ViewConfiguration.get(mContext).hasPermanentMenuKey()) {
            int navigationBarResourceId = mResources.getIdentifier("navigation_bar_height", "dimen", "android");
            navigationBarHeight = mResources.getDimensionPixelSize(navigationBarResourceId);
            mScreenInfo.setNavigationBarHeight(navigationBarHeight);
        }

        mScreenInfo.setScreenSize(calculateScreenSize());

        mScreenInfo.setDeviceModel(Build.MODEL);
        mScreenInfo.setManufacturer(Build.MANUFACTURER);
        mScreenInfo.setAndroidVersion(Build.VERSION.RELEASE);
        mScreenInfo.setAndroidCodename(getAndroidCodename());
        mScreenInfo.setAndroidApi(Build.VERSION.SDK_INT);
        mScreenInfo.setWidthPixels(width);
        mScreenInfo.setHeightPixels(height);

        double inches = calculateScreenSizeInches(width, height);
        mScreenInfo.setInches(inches);
        mScreenInfo.setDensityDpi(mDisplayMetrics.densityDpi);
        mScreenInfo.setDensity(mDisplayMetrics.density);

        mScreenInfo.setDensityCode(calculateDensityCode());
        mScreenInfo.setDensityX(mDisplayMetrics.xdpi);
        mScreenInfo.setDensityY(mDisplayMetrics.ydpi);

        TypedArray attrs = mContext.getTheme().obtainStyledAttributes(
                new int[]{android.R.attr.actionBarSize});
        int actionBarHeight = (int) attrs.getDimension(0, 0);
        attrs.recycle();

        mScreenInfo.setActionBarHeight(actionBarHeight);
        int contentTop = statusBarHeight + actionBarHeight;
        mScreenInfo.setContentTop(contentTop);
        mScreenInfo.setContentHeight(height - statusBarHeight - navigationBarHeight);
        mScreenInfo.setContentBottom(height - navigationBarHeight);

        return mScreenInfo;
    }

    private String getAndroidCodename() {
        int api = Build.VERSION.SDK_INT;
        switch (api) {
            case Build.VERSION_CODES.CUPCAKE:
                return CUPCAKE;
            case Build.VERSION_CODES.DONUT:
                return DONUT;
            case Build.VERSION_CODES.ECLAIR:
                return ECLAIR;
            case Build.VERSION_CODES.ECLAIR_0_1:
                return ECLAIR_0_1;
            case Build.VERSION_CODES.ECLAIR_MR1:
                return ECLAIR_MR1;
            case Build.VERSION_CODES.FROYO:
                return FROYO;
            case Build.VERSION_CODES.GINGERBREAD:
                return GINGERBREAD;
            case Build.VERSION_CODES.GINGERBREAD_MR1:
                return GINGERBREAD_MR1;
            case Build.VERSION_CODES.HONEYCOMB:
                return HONEYCOMB;
            case Build.VERSION_CODES.HONEYCOMB_MR1:
                return HONEYCOMB_MR1;
            case Build.VERSION_CODES.HONEYCOMB_MR2:
                return HONEYCOMB_MR2;
            case Build.VERSION_CODES.ICE_CREAM_SANDWICH:
                return ICE_CREAM_SANDWICH;
            case Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1:
                return ICE_CREAM_SANDWICH_MR1;
            case Build.VERSION_CODES.JELLY_BEAN:
                return JELLY_BEAN;
            case Build.VERSION_CODES.JELLY_BEAN_MR1:
                return JELLY_BEAN_MR1;
            case Build.VERSION_CODES.JELLY_BEAN_MR2:
                return JELLY_BEAN_MR2;
            case Build.VERSION_CODES.KITKAT:
                return KITKAT;
            case Build.VERSION_CODES.LOLLIPOP:
                return LOLLIPOP;
            case Build.VERSION_CODES.LOLLIPOP_MR1:
                return LOLLIPOP_MR1;
            case Build.VERSION_CODES.M:
                return MARSHMALLOW;


            default:
                return UNKNOWN;
        }
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
}
