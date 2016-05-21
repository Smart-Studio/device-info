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

package com.smartstudio.deviceinfo.analytics.dashboard;


import com.smartstudio.deviceinfo.analytics.AnalyticsManagerImpl;
import com.smartstudio.deviceinfo.analytics.AnalyticsManagerImplTest;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DashboardAnalyticsImplTest extends AnalyticsManagerImplTest {

    private DashboardAnalyticsImpl mAnalytics;

    @Test
    public void testGetScreenName() throws Exception {
        String screenName = mAnalytics.getScreenName();
        assertThat(screenName).isEqualTo(DashboardAnalyticsImpl.SCREEN_NAME);
    }

    @Test
    public void testReportAboutTap() throws Exception {
        setScreenName();
        mAnalytics.reportAboutTap();
        verifyEvent(DashboardAnalyticsImpl.SCREEN_NAME, DashboardAnalyticsImpl.TAP_ABOUT);
    }

    @Test
    public void testReportOptionsMenuOpened() throws Exception {
        setScreenName();
        mAnalytics.reportOptionsMenuOpened();
        verifyEvent(DashboardAnalyticsImpl.SCREEN_NAME, DashboardAnalyticsImpl.MENU_OPTIONS_OPENED);
    }

    @Test
    public void testReportOptionsMenuClosed() throws Exception {
        setScreenName();
        mAnalytics.reportOptionsMenuClosed();
        verifyEvent(DashboardAnalyticsImpl.SCREEN_NAME, DashboardAnalyticsImpl.MENU_OPTIONS_CLOSED);
    }

    @Override
    protected AnalyticsManagerImpl createAnalyticsManager() {
        mAnalytics = new DashboardAnalyticsImpl(mTracker, mScreenViewBuilderProvider, mEventBuilderProvider);
        return mAnalytics;
    }
}
