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
import android.content.res.Resources;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.ViewConfiguration;

import com.smartstudio.deviceinfo.injection.qualifiers.ForApplication;
import com.smartstudio.deviceinfo.model.ScreenInfo;

import java.lang.reflect.Method;

import javax.inject.Inject;

import timber.log.Timber;

/**
 * TODO Add a class header comment
 */
public class ScreenInfoManagerImpl implements ScreenInfoManager {
    private static final String METHOD_GET_RAW_WIDTH = "getRawWidth";
    private static final String METHOD_GET_RAW_HEIGHT = "getRawHeight";

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
            try {
                Method getRawWidthMethod = Display.class.getMethod(METHOD_GET_RAW_WIDTH);
                Method getRawHeightMethod = Display.class.getMethod(METHOD_GET_RAW_HEIGHT);

                width = (int) getRawWidthMethod.invoke(mDisplay);
                height = (int) getRawHeightMethod.invoke(mDisplay);
            } catch (Exception e) {
                Timber.e(e, "Error getting screen resolution");
            }

            int statusBarResourceId = mResources.getIdentifier("status_bar_height", "dimen", "android");
            int statusBarHeight = mResources.getDimensionPixelSize(statusBarResourceId);
            mScreenInfo.setStatusBarHeight(statusBarHeight);

             if (!ViewConfiguration.get(mContext).hasPermanentMenuKey()) {
                 int navigationBarResourceId = mResources.getIdentifier("navigation_bar_height", "dimen", "android");
                 int navigationBarHeight = mResources.getDimensionPixelSize(navigationBarResourceId);
                 mScreenInfo.setNavigationBarHeight(navigationBarHeight);
             }

        }

        mScreenInfo.setDeviceModel(Build.MODEL);
        mScreenInfo.setAndroidVersion(Build.VERSION.RELEASE);
        mScreenInfo.setWidthPixels(width);
        mScreenInfo.setHeightPixels(height);

        return mScreenInfo;
    }

}
