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

import android.content.Context;

import com.smartstudio.deviceinfo.controllers.about.AboutController;
import com.smartstudio.deviceinfo.injection.qualifiers.ForActivity;
import com.smartstudio.deviceinfo.injection.scopes.PerActivity;
import com.smartstudio.deviceinfo.ui.BaseView;
import com.smartstudio.deviceinfo.ui.about.AboutView;
import com.smartstudio.deviceinfo.ui.about.AboutViewImpl;

import dagger.Module;
import dagger.Provides;

@Module
public class AboutModule {

    private final AboutController mController;
    private final Context mContext;

    public AboutModule(AboutController controller, Context context) {
        mController = controller;
        mContext = context;
    }

    @Provides
    @PerActivity
    @ForActivity
    Context provideContext() {
        return mContext;
    }

    @Provides
    AboutController provideController() {
        return mController;
    }

    @Provides
    @PerActivity
    AboutView provideView(AboutViewImpl view) {
        return view;
    }

    @Provides
    @PerActivity
    BaseView provideBaseView(AboutView view) {
        return view;
    }
}
