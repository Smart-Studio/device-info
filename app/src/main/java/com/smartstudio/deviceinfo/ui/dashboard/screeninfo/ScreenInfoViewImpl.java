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
import com.smartstudio.deviceinfo.model.ScreenInfo;
import com.smartstudio.deviceinfo.ui.BaseViewImpl;
import com.smartstudio.deviceinfo.ui.PropertyLayout;
import com.smartstudio.deviceinfo.utils.Utils;

import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;

public class ScreenInfoViewImpl extends BaseViewImpl implements ScreenInfoView {

    @BindView(R.id.view_device_name)
    PropertyLayout mViewDeviceName;

    @BindView(R.id.view_device_manufacturer)
    PropertyLayout mViewManufacturer;

    @BindView(R.id.view_screen_res)
    PropertyLayout mViewScreenRes;

    @BindView(R.id.view_screen_status)
    PropertyLayout mViewScreenStatus;

    @BindView(R.id.view_screen_navigation)
    PropertyLayout mViewScreenNavigation;

    @BindView(R.id.view_screen_size)
    PropertyLayout mViewScreenSize;

    @BindView(R.id.view_screen_inches)
    PropertyLayout mViewScreenInches;

    @BindView(R.id.view_screen_density)
    PropertyLayout mViewScreenDensity;

    @BindView(R.id.view_screen_density_code)
    PropertyLayout mViewScreenDensityCode;

    @BindView(R.id.view_screen_density_x)
    PropertyLayout mViewScreenDensityX;

    @BindView(R.id.view_screen_density_y)
    PropertyLayout mViewScreenDensityY;

    @BindView(R.id.view_screen_action_bar)
    PropertyLayout mViewActionBarHeight;

    @BindView(R.id.view_screen_content_top)
    PropertyLayout mViewContentTop;

    @BindView(R.id.view_screen_content_bottom)
    PropertyLayout mViewContentBottom;

    @BindView(R.id.view_screen_content_height)
    PropertyLayout mViewContentHeight;

    @Inject
    public ScreenInfoViewImpl() {

    }

    @Override
    public int getLayoutResourceId() {
        return R.layout.fragment_screen_info;
    }

    @Override
    public void init(View view) {
        super.init(view);
    }

    @Override
    public void showScreenInfo(ScreenInfo screenInfo) {
        mViewDeviceName.setValue(screenInfo.getDeviceModel());
        mViewManufacturer.setValue(screenInfo.getManufacturer());

        double density = screenInfo.getDensity();

        String screenRes = String.format(Locale.getDefault(), "%dx%d px (%dx%d dp)", screenInfo.getWidthPixels(),
                screenInfo.getHeightPixels(), Utils.pxToDp(screenInfo.getWidthPixels(), density), Utils.pxToDp(screenInfo.getHeightPixels(), density));
        mViewScreenRes.setValue(screenRes);

        String statusBarHeight = String.format(Locale.getDefault(), "%d px (%d dp)",
                screenInfo.getStatusBarHeight(), Utils.pxToDp(screenInfo.getStatusBarHeight(), density));
        mViewScreenStatus.setValue(statusBarHeight);

        int navBarHeight = screenInfo.getNavigationBarHeight();
        if (navBarHeight == 0) {
            mViewScreenNavigation.setVisibility(View.GONE);
        } else {
            String navigationBarHeight = String.format(Locale.getDefault(), "%d px (%d dp)",
                    screenInfo.getNavigationBarHeight(), Utils.pxToDp(screenInfo.getNavigationBarHeight(), density));
            mViewScreenNavigation.setValue(navigationBarHeight);
        }

        mViewScreenSize.setValue(screenInfo.getScreenSize());

        String inches = String.format(Locale.getDefault(), "%.1f\"", screenInfo.getInches());
        mViewScreenInches.setValue(inches);
        String densityText = String.format(Locale.getDefault(), "%d dp (x%.1f)", screenInfo.getDensityDpi()
                , screenInfo.getDensity());
        mViewScreenDensity.setValue(densityText);
        mViewScreenDensityCode.setValue(screenInfo.getDensityCode());
        String densityX = String.format(Locale.getDefault(), "%.2f dp", screenInfo.getDensityX());
        mViewScreenDensityX.setValue(densityX);
        String densityY = String.format(Locale.getDefault(), "%.2f dp", screenInfo.getDensityY());
        mViewScreenDensityY.setValue(densityY);

        String actionBarHeight = String.format(Locale.getDefault(), "%d px (%d dp)", screenInfo.getActionBarHeight(),
                Utils.pxToDp(screenInfo.getActionBarHeight(), density));
        mViewActionBarHeight.setValue(String.valueOf(actionBarHeight));
        String contentHeight = String.format(Locale.getDefault(), "%d px (%d dp)", screenInfo.getContentHeight(),
                Utils.pxToDp(screenInfo.getContentHeight(), density));
        mViewContentHeight.setValue(contentHeight);
        String contentTop = String.format(Locale.getDefault(), "%d px (%d dp)", screenInfo.getContentTop(),
                Utils.pxToDp(screenInfo.getContentTop(), density));
        mViewContentTop.setValue(String.valueOf(contentTop));
        String contentBottom = String.format(Locale.getDefault(), "%d px (%d dp)", screenInfo.getContentBottom(),
                Utils.pxToDp(screenInfo.getContentBottom(), density));
        mViewContentBottom.setValue(String.valueOf(contentBottom));
    }
}
