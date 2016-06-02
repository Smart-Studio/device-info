package com.smartstudio.deviceinfo.analytics.dashboard.screeninfo;


import com.crashlytics.android.answers.Answers;
import com.crashlytics.android.answers.ContentViewEvent;
import com.smartstudio.deviceinfo.analytics.FabricAnalyticsManager;

import javax.inject.Inject;

public class ScreenInfoFabricAnalytics extends FabricAnalyticsManager implements ScreenInfoAnalytics {
    static final String SCREEN_NAME = "Screen info";
    static final String TAP_ABOUT = "Tap menu about";
    static final String MENU_OPTIONS_OPENED = "Menu options opened";
    static final String MENU_OPTIONS_CLOSED = "Menu options closed";
    static final String CONTENT_TYPE = "Fragment";

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
        event.putContentName(mScreenName)
                .putContentId(String.valueOf(mScreenName.hashCode()))
                .putContentType(CONTENT_TYPE);
    }
}
