package com.smartstudio.deviceinfo.analytics.dashboard.system;


import com.crashlytics.android.answers.Answers;
import com.crashlytics.android.answers.ContentViewEvent;
import com.smartstudio.deviceinfo.analytics.FabricAnalyticsManager;

import javax.inject.Inject;

public class SystemFabricAnalytics extends FabricAnalyticsManager implements SystemAnalytics {
    static final String SCREEN_NAME = "System info";
    static final String CONTENT_TYPE = "fragment";

    @Inject
    public SystemFabricAnalytics(Answers answers, ContentViewEvent contentViewEvent) {
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
