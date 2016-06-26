package com.smartstudio.deviceinfo.injection.modules;


import android.content.Context;

import com.crashlytics.android.answers.Answers;
import com.crashlytics.android.answers.ContentViewEvent;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.smartstudio.deviceinfo.injection.qualifiers.ForApplication;
import com.smartstudio.deviceinfo.injection.scopes.PerApplication;

import dagger.Module;
import dagger.Provides;

/**
 * Module that provides analytics dependencies
 */
@Module
public class AnalyticsModule {

    @Provides
    FirebaseAnalytics provideAnalytics(@ForApplication Context context) {
        return FirebaseAnalytics.getInstance(context);
    }

    @Provides
    @PerApplication
    Answers provideFabricAnswers() {
        return Answers.getInstance();
    }

    @Provides
    ContentViewEvent provideContentViewEvent() {
        return new ContentViewEvent();
    }
}
