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

package com.smartstudio.deviceinfo.injection.modules;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;

import com.crashlytics.android.Crashlytics;
import com.smartstudio.deviceinfo.BuildConfig;
import com.smartstudio.deviceinfo.injection.qualifiers.ForApplication;
import com.smartstudio.deviceinfo.injection.scopes.PerApplication;
import com.smartstudio.deviceinfo.logging.DebugTree;
import com.smartstudio.deviceinfo.logging.ReleaseTree;

import dagger.Module;
import dagger.Provides;
import timber.log.Timber;

@Module
public class AppModule {
    private final Application mApp;

    public AppModule(Application app) {
        mApp = app;
    }

    @Provides
    @PerApplication
    @ForApplication
    Context provideContext() {
        return mApp;
    }

    @Provides
    @PerApplication
    Resources provideResources() {
        return mApp.getResources();
    }

    @Provides
    @PerApplication
    Application provideApplication() {
        return mApp;
    }

    @Provides
    @PerApplication
    Timber.Tree provideTimberTree() {
        return BuildConfig.DEBUG ? new DebugTree() : new ReleaseTree();
    }

    @Provides
    @PerApplication
    Crashlytics provideCrashlytics() {
        return new Crashlytics();
    }

    @Provides
    Intent provideIntent() {
        return new Intent();
    }

    @Provides
    IntentFilter provideIntentFilter() {
        return new IntentFilter();
    }
}
