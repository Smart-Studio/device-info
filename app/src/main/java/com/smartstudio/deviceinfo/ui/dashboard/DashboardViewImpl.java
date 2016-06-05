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

package com.smartstudio.deviceinfo.ui.dashboard;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.smartstudio.deviceinfo.R;
import com.smartstudio.deviceinfo.controllers.dashboard.DashboardController;
import com.smartstudio.deviceinfo.ui.BaseActionBarView;

import javax.inject.Inject;

import butterknife.BindView;

public class DashboardViewImpl extends BaseActionBarView implements DashboardView {

    @BindView(R.id.tabs_dashboard)
    TabLayout mTabs;
    @BindView(R.id.pager_dashboard)
    ViewPager mPager;

    private final DashboardPagerAdapter mPagerAdapter;

    @Inject
    public DashboardViewImpl(DashboardController controller, DashboardPagerAdapter pagerAdapter) {
        super(controller);
        mPagerAdapter = pagerAdapter;
    }

    @Override
    protected int getToolbarId() {
        return R.id.toolbar_dashboard;
    }

    @Override
    public int getLayoutResourceId() {
        return R.layout.activity_dashboard;
    }

    @Override
    public void init(View view) {
        super.init(view);
        mPager.setAdapter(mPagerAdapter);
        mTabs.setupWithViewPager(mPager);
    }


    @Override
    public void showShareDialog() {
        mPagerAdapter.getItemAt(mPager.getCurrentItem()).onSharedClicked();
    }
}
