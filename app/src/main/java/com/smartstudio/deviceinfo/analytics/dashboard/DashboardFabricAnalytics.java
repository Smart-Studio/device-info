package com.smartstudio.deviceinfo.analytics.dashboard;

import com.crashlytics.android.answers.Answers;
import com.crashlytics.android.answers.ContentViewEvent;
import com.smartstudio.deviceinfo.analytics.FabricAnalyticsManager;

import javax.inject.Inject;

public class DashboardFabricAnalytics extends FabricAnalyticsManager implements DashboardAnalytics {
    static final String SCREEN_NAME = "Dashboard";
    static final String TAP_SHARE = "Tap menu share";
    static final String TAP_ABOUT = "Tap menu about";
    static final String MENU_OPTIONS_OPENED = "Menu options opened";
    static final String MENU_OPTIONS_CLOSED = "Menu options closed";
    static final String CONTENT_TYPE = "Activity";

    @Inject
    DashboardFabricAnalytics(Answers answers, ContentViewEvent contentViewEvent) {
        super(answers, contentViewEvent);
    }

    @Override
    protected String getScreenName() {
        return SCREEN_NAME;
    }

    @Override
    protected void setupContentViewEvent(ContentViewEvent event) {
        event.putContentName(mScreenName)
                .putContentId(String.valueOf(mScreenName.hashCode()))
                .putContentType(CONTENT_TYPE);
    }

    @Override
    public void reportShareTap() {
        reportEvent(TAP_SHARE);
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
