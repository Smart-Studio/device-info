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

import com.smartstudio.deviceinfo.model.ScreenInfo;

import javax.inject.Inject;

public class ScreenInfoManagerImpl implements ScreenInfoManager {
    public static final String DEVICE_MODEL = "Nexus 4";
    public static final String MANUFACTURER = "LGE";
    public static final String ANDROID_VERSION = "4.3";
    public static final String ANDROID_CODENAME = "Jelly Bean MR2";
    public static final int ANDROID_API = 18;
    public static final int WIDTH = 768;
    public static final int HEIGHT = 1280;
    public static final double INCHES = 4.7;
    public static final int STATUS_BAR_HEIGHT = 50;
    public static final int NAVIGATION_BAR_HEIGHT = 96;
    public static final String SCREEN_SIZE = "normal";
    public static final int DENSITY_DPI = 320;
    public static final double DENSITY = 2.0;
    public static final String DENSITY_CODE = "xhdpi";
    public static final double DENSITY_X = 319.79;
    public static final double DENSITY_Y = 318.74;
    public static final int ACTION_BAR_HEIGHT = 96;
    public static final int CONTENT_TOP = 146;
    public static final int CONTENT_BOTTOM = 1184;
    public static final int CONTENT_HEIGHT = 1134;

    @Inject
    public ScreenInfoManagerImpl() {
    }

    @Override
    public ScreenInfo getScreenInfo() {
        ScreenInfo screenInfo = new ScreenInfo();
        screenInfo.setDeviceModel(DEVICE_MODEL);
        screenInfo.setManufacturer(MANUFACTURER);
        screenInfo.setAndroidVersion(ANDROID_VERSION);
        screenInfo.setAndroidCodename(ANDROID_CODENAME);
        screenInfo.setAndroidApi(ANDROID_API);
        screenInfo.setWidthPixels(WIDTH);
        screenInfo.setHeightPixels(HEIGHT);
        screenInfo.setInches(INCHES);
        screenInfo.setStatusBarHeight(STATUS_BAR_HEIGHT);
        screenInfo.setNavigationBarHeight(NAVIGATION_BAR_HEIGHT);
        screenInfo.setScreenSize(SCREEN_SIZE);
        screenInfo.setDensityDpi(DENSITY_DPI);
        screenInfo.setDensity(DENSITY);
        screenInfo.setDensityCode(DENSITY_CODE);
        screenInfo.setDensityX(DENSITY_X);
        screenInfo.setDensityY(DENSITY_Y);
        screenInfo.setActionBarHeight(ACTION_BAR_HEIGHT);
        screenInfo.setContentTop(CONTENT_TOP);
        screenInfo.setContentBottom(CONTENT_BOTTOM);
        screenInfo.setContentHeight(CONTENT_HEIGHT);
        return screenInfo;
    }
}
