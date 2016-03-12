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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.smartstudio.deviceinfo.controllers.about.attributions.AttributionsController;
import com.smartstudio.deviceinfo.injection.qualifiers.ForApplication;
import com.smartstudio.deviceinfo.injection.scopes.PerActivity;
import com.smartstudio.deviceinfo.logic.AttributionsProvider;
import com.smartstudio.deviceinfo.logic.AttributionsProviderImpl;
import com.smartstudio.deviceinfo.ui.BaseView;
import com.smartstudio.deviceinfo.ui.about.attributions.AttributionsAdapter;
import com.smartstudio.deviceinfo.ui.about.attributions.AttributionsAdapterImpl;
import com.smartstudio.deviceinfo.ui.about.attributions.AttributionsView;
import com.smartstudio.deviceinfo.ui.about.attributions.AttributionsViewImpl;

import dagger.Module;
import dagger.Provides;

@Module
public class AttributionsModule {

    private final AttributionsController mController;

    public AttributionsModule(AttributionsController controller) {
        mController = controller;
    }

    @Provides
    AttributionsController provideController() {
        return mController;
    }

    @Provides
    @PerActivity
    AttributionsView provideView(AttributionsViewImpl view) {
        return view;
    }

    @Provides
    @PerActivity
    BaseView provideBaseView(AttributionsView view) {
        return view;
    }

    @Provides
    @PerActivity
    AttributionsProvider provideProvider(AttributionsProviderImpl provider) {
        return provider;
    }

    @Provides
    @PerActivity
    AttributionsAdapter provideAdapter(AttributionsAdapterImpl adapter) {
        return adapter;
    }

    @Provides
    RecyclerView.LayoutManager provideLayoutManager(@ForApplication Context context) {
        return new LinearLayoutManager(context);
    }
}
