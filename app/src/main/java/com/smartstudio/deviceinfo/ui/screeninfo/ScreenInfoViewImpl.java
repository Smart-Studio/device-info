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

package com.smartstudio.deviceinfo.ui.screeninfo;

import android.support.v7.widget.Toolbar;
import android.view.View;

import com.smartstudio.deviceinfo.R;
import com.smartstudio.deviceinfo.controllers.screeninfo.ScreenInfoController;
import com.smartstudio.deviceinfo.model.ScreenInfo;
import com.smartstudio.deviceinfo.ui.PropertyLayout;
import com.smartstudio.deviceinfo.utils.Utils;

import java.util.Locale;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * TODO Add a class header comment
 */
public class ScreenInfoViewImpl implements ScreenInfoView {
    private final ScreenInfoController mController;

    @Bind(R.id.toolbar_screen_info)
    Toolbar mToolbar;

    @Bind(R.id.view_device_name)
    PropertyLayout mViewDeviceName;

    @Bind(R.id.view_device_manufacturer)
    PropertyLayout mViewManufacturer;

    @Bind(R.id.view_os)
    PropertyLayout mViewOs;

    @Bind(R.id.view_os_codename)
    PropertyLayout mViewOsCodename;

    @Bind(R.id.view_os_api)
    PropertyLayout mViewOsApi;

    @Bind(R.id.view_screen_res)
    PropertyLayout mViewScreenRes;

    @Bind(R.id.view_screen_status)
    PropertyLayout mViewScreenStatus;

    @Bind(R.id.view_screen_navigation)
    PropertyLayout mViewScreenNavigation;

    @Bind(R.id.view_screen_size)
    PropertyLayout mViewScreenSize;

    @Bind(R.id.view_screen_inches)
    PropertyLayout mViewScreenInches;

    @Bind(R.id.view_screen_density)
    PropertyLayout mViewScreenDensity;

    @Bind(R.id.view_screen_density_code)
    PropertyLayout mViewScreenDensityCode;

    @Bind(R.id.view_screen_density_x)
    PropertyLayout mViewScreenDensityX;

    @Bind(R.id.view_screen_density_y)
    PropertyLayout mViewScreenDensityY;

    @Bind(R.id.view_screen_action_bar)
    PropertyLayout mViewActionBarHeight;

    @Bind(R.id.view_screen_content_top)
    PropertyLayout mViewContentTop;

    @Bind(R.id.view_screen_content_bottom)
    PropertyLayout mViewContentBottom;

    @Bind(R.id.view_screen_content_height)
    PropertyLayout mViewContentHeight;

    @Inject
    public ScreenInfoViewImpl(ScreenInfoController controller) {
        mController = controller;
    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_screen_info;
    }

    @Override
    public void init(View view) {
        ButterKnife.bind(this, view);
        mController.setUpToolBar(mToolbar);
    }

    @Override
    public void showScreenInfo(ScreenInfo screenInfo) {
        mViewDeviceName.setValue(screenInfo.getDeviceModel());
        mViewManufacturer.setValue(screenInfo.getManufacturer());
        mViewOs.setValue(screenInfo.getAndroidVersion());
        mViewOsCodename.setValue(screenInfo.getAndroidCodename());
        mViewOsApi.setValue(String.valueOf(screenInfo.getAndroidApi()));

        String screenRes = String.format(Locale.getDefault(), "%dx%d px (%dx%d dp)", screenInfo.getWidthPixels(),
                screenInfo.getHeightPixels(), Utils.pxToDp(screenInfo.getWidthPixels()), Utils.pxToDp(screenInfo.getHeightPixels()));
        mViewScreenRes.setValue(screenRes);

        String statusBarHeight = String.format(Locale.getDefault(), "%d px (%d dp)",
                screenInfo.getStatusBarHeight(), Utils.pxToDp(screenInfo.getStatusBarHeight()));
        mViewScreenStatus.setValue(statusBarHeight);

        int navBarHeight = screenInfo.getNavigationBarHeight();
        if (navBarHeight == 0) {
            mViewScreenNavigation.setVisibility(View.GONE);
        } else {
            String navigationBarHeight = String.format(Locale.getDefault(), "%d px (%d dp)",
                    screenInfo.getNavigationBarHeight(), Utils.pxToDp(screenInfo.getNavigationBarHeight()));
            mViewScreenNavigation.setValue(navigationBarHeight);
        }

        mViewScreenSize.setValue(screenInfo.getScreenSize());

        String inches = String.format(Locale.getDefault(), "%.1f\"", screenInfo.getInches());
        mViewScreenInches.setValue(inches);
        String density = String.format(Locale.getDefault(), "%d dp (x%.1f)", screenInfo.getDensityDpi()
                , screenInfo.getDensity());
        mViewScreenDensity.setValue(density);
        mViewScreenDensityCode.setValue(screenInfo.getDensityCode());
        String densityX = String.format(Locale.getDefault(), "%.2f dp", screenInfo.getDensityX());
        mViewScreenDensityX.setValue(densityX);
        String densityY = String.format(Locale.getDefault(), "%.2f dp", screenInfo.getDensityY());
        mViewScreenDensityY.setValue(densityY);

        String actionBarHeight = String.format(Locale.getDefault(), "%d px (%d dp)", screenInfo.getActionBarHeight(),
                Utils.pxToDp(screenInfo.getActionBarHeight()));
        mViewActionBarHeight.setValue(String.valueOf(actionBarHeight));
        String contentHeight = String.format(Locale.getDefault(), "%d px (%d dp)", screenInfo.getContentHeight(),
                Utils.pxToDp(screenInfo.getContentHeight()));
        mViewContentHeight.setValue(contentHeight);
        String contentTop = String.format(Locale.getDefault(), "%d px (%d dp)", screenInfo.getContentTop(),
                Utils.pxToDp(screenInfo.getContentTop()));
        mViewContentTop.setValue(String.valueOf(contentTop));
        String contentBottom = String.format(Locale.getDefault(), "%d px (%d dp)", screenInfo.getContentBottom(),
                Utils.pxToDp(screenInfo.getContentBottom()));
        mViewContentBottom.setValue(String.valueOf(contentBottom));

    }
}
