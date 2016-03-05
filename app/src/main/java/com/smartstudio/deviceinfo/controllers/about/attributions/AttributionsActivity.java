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

package com.smartstudio.deviceinfo.controllers.about.attributions;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;

import com.smartstudio.deviceinfo.DeviceInfoApp;
import com.smartstudio.deviceinfo.controllers.BaseActivity;
import com.smartstudio.deviceinfo.exceptions.BrowserNotFoundException;
import com.smartstudio.deviceinfo.injection.Injector;
import com.smartstudio.deviceinfo.logic.AttributionsProvider;
import com.smartstudio.deviceinfo.ui.about.attributions.AttributionsView;
import com.smartstudio.deviceinfo.utils.Utils;

import javax.inject.Inject;

import timber.log.Timber;

public class AttributionsActivity extends BaseActivity implements AttributionsController {

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, AttributionsActivity.class);
        activity.startActivity(intent);
    }

    @Inject
    AttributionsView mView;

    @Inject
    AttributionsProvider mAttributionsProvider;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mView.showAttributions(mAttributionsProvider.getAttributions());
    }

    @Override
    protected void initComponent() {
        DeviceInfoApp.get()
                .getComponent()
                .plus(Injector.provideAttributionsModule(this))
                .inject(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }

        return true;
    }

    @Override
    public void onAttributionClicked(String repoUrl) {
        try {
            Utils.openUrl(this, repoUrl);
        } catch (BrowserNotFoundException e) {
            Timber.e(e, "Error opening link to %s", repoUrl);
        }
    }
}
