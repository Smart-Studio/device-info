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

package com.smartstudio.deviceinfo.controllers.dashboard.screeninfo;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.smartstudio.deviceinfo.analytics.dashboard.screeninfo.ScreenInfoAnalytics;
import com.smartstudio.deviceinfo.controllers.BaseFragment;
import com.smartstudio.deviceinfo.controllers.dashboard.DashboardActivity;
import com.smartstudio.deviceinfo.injection.Injector;
import com.smartstudio.deviceinfo.injection.qualifiers.ForFabric;
import com.smartstudio.deviceinfo.injection.qualifiers.ForGoogle;
import com.smartstudio.deviceinfo.logic.dashboard.ScreenInfoManager;
import com.smartstudio.deviceinfo.model.ScreenInfo;
import com.smartstudio.deviceinfo.ui.dashboard.screeninfo.ScreenInfoView;

import javax.inject.Inject;

public class ScreenInfoFragment extends BaseFragment implements ScreenInfoController {

    public static ScreenInfoFragment newInstance() {
        return new ScreenInfoFragment();
    }

    @Inject
    ScreenInfoView mView;

    @Inject
    ScreenInfoManager mScreenInfoManager;

    @Inject
    @ForGoogle
    ScreenInfoAnalytics mAnalytics;

    @Inject
    @ForFabric
    ScreenInfoAnalytics mFabricAnalytics;


    @Override
    protected void initComponent(Context context) {
        DashboardActivity activity = (DashboardActivity) context;
        activity.getComponent()
                .plus(Injector.provideScreenInfoModule(this, activity.getWindowManager().getDefaultDisplay()))
                .inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(mView.getLayoutResourceId(), container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ScreenInfo screenInfo = mScreenInfoManager.getScreenInfo();
        mView.showScreenInfo(screenInfo);
    }

    @Override
    public void onResume() {
        super.onResume();
        mAnalytics.reportScreen();
        mFabricAnalytics.reportScreen();
    }

    @Override
    public void onSharedClicked() {
        mView.showShareDialog();
        mAnalytics.reportShare();
        mFabricAnalytics.reportShare();
    }
}