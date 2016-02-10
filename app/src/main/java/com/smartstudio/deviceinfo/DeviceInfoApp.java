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

package com.smartstudio.deviceinfo;

import android.app.Application;

import com.crashlytics.android.Crashlytics;
import com.smartstudio.deviceinfo.injection.Injector;
import com.smartstudio.deviceinfo.injection.components.AppComponent;
import com.smartstudio.deviceinfo.injection.components.DaggerAppComponent;

import javax.inject.Inject;

import io.fabric.sdk.android.Fabric;
import timber.log.Timber;

public class DeviceInfoApp extends Application {
    private static DeviceInfoApp sApp;

    public static DeviceInfoApp get() {
        return sApp;
    }

    @Inject
    Timber.Tree mLoggerTree;
    @Inject
    Crashlytics mCrashlytics;

    private final AppComponent mComponent;

    public DeviceInfoApp() {
        sApp = this;

        mComponent = DaggerAppComponent
                .builder()
                .appModule(Injector.injectAppModule(sApp))
                .build();
        mComponent.inject(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(mLoggerTree);
        initCrashlytics();
    }

    public AppComponent getComponent() {
        return mComponent;
    }

    private void initCrashlytics(){
        Fabric.with(this, mCrashlytics);
    }
}
