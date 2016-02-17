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

package com.smartstudio.deviceinfo.ui;

import android.support.v7.widget.Toolbar;
import android.view.View;

import com.smartstudio.deviceinfo.R;
import com.smartstudio.deviceinfo.controllers.ScreenInfoController;
import com.smartstudio.deviceinfo.model.ScreenInfo;
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

    @Bind(R.id.view_os)
    PropertyLayout mViewOs;

    @Bind(R.id.view_screen_res)
    PropertyLayout mViewScreenRes;

    @Bind(R.id.view_screen_status)
    PropertyLayout mViewScreenStatus;

    @Bind(R.id.view_screen_navigation)
    PropertyLayout mViewScreenNavigation;

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
        mViewOs.setValue(screenInfo.getAndroidVersion());

        String screenRes = String.format(Locale.getDefault(), "%dx%d px (%dx%d) dp", screenInfo.getWidthPixels(),
                screenInfo.getHeightPixels(), Utils.pxToDp(screenInfo.getWidthPixels()), Utils.pxToDp(screenInfo.getHeightPixels()));
        mViewScreenRes.setValue(screenRes);

        mViewScreenStatus.setValue(screenInfo.getStatusBarHeight() + " px");

        int navBarHeight = screenInfo.getNavigationBarHeight();
        if (navBarHeight != 0) {
            mViewScreenNavigation.setValue(screenInfo.getNavigationBarHeight() + " px");
        }
    }
}
