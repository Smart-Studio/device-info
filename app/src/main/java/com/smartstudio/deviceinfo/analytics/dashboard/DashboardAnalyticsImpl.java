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

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.smartstudio.deviceinfo.analytics.AnalyticsManagerImpl;

import javax.inject.Inject;
import javax.inject.Provider;

public class DashboardAnalyticsImpl extends AnalyticsManagerImpl implements DashboardAnalytics {
    static final String SCREEN_NAME = "Dashboard";
    static final String TAP_ABOUT = "Tap menu about";
    static final String MENU_OPTIONS_OPENED = "Menu options opened";
    static final String MENU_OPTIONS_CLOSED = "Menu options closed";

    /**
     * Creates an instance of the AnalyticsManagerImpl
     *
     * @param tracker                   Google analytics tracker singleton instance
     * @param screenViewBuilderProvider Provides a screen view builder to track screens
     * @param eventBuilderProvider      Provides an event builder to track events
     **/
    @Inject
    public DashboardAnalyticsImpl(Tracker tracker,
                                  Provider<HitBuilders.ScreenViewBuilder> screenViewBuilderProvider,
                                  Provider<HitBuilders.EventBuilder> eventBuilderProvider) {
        super(tracker, screenViewBuilderProvider, eventBuilderProvider);
    }

    @Override
    protected String getScreenName() {
        return SCREEN_NAME;
    }

    @Override
    public void reportAboutTap() {
        reportEvent(TAP_ABOUT);
    }

    @Override
    public void reportOptionsMenuOpened() {
        reportEvent(MENU_OPTIONS_OPENED);
    }

    @Override
    public void reportOptionsMenuClosed() {
        reportEvent(MENU_OPTIONS_CLOSED);
    }
}
