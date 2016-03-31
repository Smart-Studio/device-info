package com.smartstudio.deviceinfo.analytics.screeninfo;


import com.crashlytics.android.answers.Answers;
import com.crashlytics.android.answers.ContentViewEvent;
import com.smartstudio.deviceinfo.analytics.FabricAnalyticsManager;

import javax.inject.Inject;

public class ScreenInfoFabricAnalytics extends FabricAnalyticsManager implements ScreenInfoAnalytics {
    static final String SCREEN_NAME = "Screen info";
    static final String TAP_ABOUT = "Tap menu about";
    static final String MENU_OPTIONS_OPENED = "Menu options opened";
    static final String MENU_OPTIONS_CLOSED = "Menu options closed";
    static final String CONTENT_TYPE = "Activity";

    @Inject
    public ScreenInfoFabricAnalytics(Answers answers, ContentViewEvent contentViewEvent) {
        super(answers, contentViewEvent);
    }

    @Override
    protected String getScreenName() {
        return SCREEN_NAME;
    }

    @Override
    protected void setupContentViewEvent(ContentViewEvent event) {
        event.putContentName(mScreenName);
        event.putContentId(String.valueOf(mScreenName.hashCode()));
        event.putContentType(CONTENT_TYPE);
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
