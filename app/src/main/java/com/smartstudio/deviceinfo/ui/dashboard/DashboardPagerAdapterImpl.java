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

import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.smartstudio.deviceinfo.R;
import com.smartstudio.deviceinfo.controllers.dashboard.screeninfo.ScreenInfoFragment;

import javax.inject.Inject;

public class DashboardPagerAdapterImpl extends DashboardPagerAdapter {
    static final int PAGE_COUNT = 1;
    static final int POS_SCREEN_TAB = 0;
    static final int POS_OS_TAB = 1;

    private final Resources mResources;

    @Inject
    public DashboardPagerAdapterImpl(FragmentManager fm, Resources resources) {
        super(fm);
        mResources = resources;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case POS_SCREEN_TAB:
                return ScreenInfoFragment.newInstance();
            case POS_OS_TAB:
                break;
            default:
                throw new IllegalStateException("Page not supported");
        }


        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mResources.getString(R.string.tab_screen_info);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }
}
