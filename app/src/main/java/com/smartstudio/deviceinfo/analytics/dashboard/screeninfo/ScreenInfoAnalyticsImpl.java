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


import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.smartstudio.deviceinfo.analytics.AnalyticsManagerImpl;

import javax.inject.Inject;
import javax.inject.Provider;

public class ScreenInfoAnalyticsImpl extends AnalyticsManagerImpl implements ScreenInfoAnalytics {
    static final String SCREEN_NAME = "Screen info";

    @Inject
    public ScreenInfoAnalyticsImpl(Tracker tracker,
                                   Provider<HitBuilders.ScreenViewBuilder> screenViewBuilderProvider,
                                   Provider<HitBuilders.EventBuilder> eventBuilderProvider) {
        super(tracker, screenViewBuilderProvider, eventBuilderProvider);
    }

    @Override
    protected String getScreenName() {
        return SCREEN_NAME;
    }
}
