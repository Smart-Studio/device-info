package com.smartstudio.deviceinfo.analytics.about.attributions;


import com.crashlytics.android.answers.Answers;
import com.crashlytics.android.answers.ContentViewEvent;
import com.crashlytics.android.answers.CustomEvent;
import com.smartstudio.deviceinfo.analytics.FabricAnalyticsManager;

import javax.inject.Inject;

public class AttributionsFabricAnalytics extends FabricAnalyticsManager implements AttributionsAnalytics {
    static final String SCREEN_NAME = "Attributions";
    static final String CONTENT_TYPE = "Activity";
    static final String TAP_ATTRIBUTION = "Tap attribution";
    static final String ATTRIBUTION_LIBRARY = "Library";
    static final String TAP_ACTION_BAR_BACK = "Tap action bar back";

    @Inject
    public AttributionsFabricAnalytics(Answers answers, ContentViewEvent contentViewEvent) {
        super(answers, contentViewEvent);
    }

    @Override
    public void reportAttributionTap(String library) {
        CustomEvent event = buildCustomEvent(TAP_ATTRIBUTION)
                .putCustomAttribute(ATTRIBUTION_LIBRARY, library);
        reportEvent(event);
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
