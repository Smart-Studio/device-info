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

package com.smartstudio.deviceinfo.analytics.dashboard.screeninfo;

import com.smartstudio.deviceinfo.analytics.AnalyticsManagerImpl;
import com.smartstudio.deviceinfo.analytics.AnalyticsManagerImplTest;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ScreenInfoAnalyticsImplTest extends AnalyticsManagerImplTest {
    private ScreenInfoAnalyticsImpl mAnalytics;

    @Test
    public void testGetScreenName() throws Exception {
        String screenName = mAnalytics.getScreenName();
        assertThat(screenName).isEqualTo(ScreenInfoAnalyticsImpl.SCREEN_NAME);
    }

    @Override
    protected AnalyticsManagerImpl createAnalyticsManager() {
        mAnalytics = new ScreenInfoAnalyticsImpl(mTracker, mScreenViewBuilderProvider, mEventBuilderProvider);
        return mAnalytics;
    }
}