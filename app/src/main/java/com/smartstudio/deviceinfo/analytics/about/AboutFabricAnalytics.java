package com.smartstudio.deviceinfo.analytics.about;

import com.crashlytics.android.answers.Answers;
import com.crashlytics.android.answers.ContentViewEvent;
import com.smartstudio.deviceinfo.analytics.FabricAnalyticsManager;

import javax.inject.Inject;

public class AboutFabricAnalytics extends FabricAnalyticsManager implements AboutAnalytics {
    static final String SCREEN_NAME = "About";
    static final String CONTENT_TYPE = "Activity";
    static final String TAP_OPEN_SOURCE = "Tap open source link";
    static final String TAP_ATTRIBUTIONS = "Tap attributions";
    static final String TAP_ACTION_BAR_BACK = "Tap action bar back";

    @Inject
    AboutFabricAnalytics(Answers answers, ContentViewEvent contentViewEvent) {
        super(answers, contentViewEvent);
    }

    @Override
    public void reportOpenSourceTap() {
        reportEvent(TAP_OPEN_SOURCE);
    }

    @Override
    public void reportAttributionsTap() {
        reportEvent(TAP_ATTRIBUTIONS);
    }

    @Override
    public void reportActionBarBackTap() {
        reportEvent(TAP_ACTION_BAR_BACK);
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
