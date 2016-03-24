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

package com.smartstudio.deviceinfo.controllers.about;

import android.app.Activity;
import android.content.Intent;
import android.view.MenuItem;

import com.smartstudio.deviceinfo.BuildConfig;
import com.smartstudio.deviceinfo.DeviceInfoApp;
import com.smartstudio.deviceinfo.analytics.about.AboutAnalytics;
import com.smartstudio.deviceinfo.controllers.BaseActivity;
import com.smartstudio.deviceinfo.controllers.about.attributions.AttributionsActivity;
import com.smartstudio.deviceinfo.exceptions.BrowserNotFoundException;
import com.smartstudio.deviceinfo.injection.Injector;
import com.smartstudio.deviceinfo.ui.about.AboutView;
import com.smartstudio.deviceinfo.utils.Utils;

import javax.inject.Inject;

import timber.log.Timber;


public class AboutActivity extends BaseActivity implements AboutController {

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, AboutActivity.class);
        activity.startActivity(intent);
    }

    @Inject
    AboutView mView;
    @Inject
    AboutAnalytics mAnalytics;

    @Override
    protected void initComponent() {
        DeviceInfoApp.get()
                .getComponent().plus(Injector.provideAboutModule(this, this))
                .inject(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mAnalytics.reportScreen();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mAnalytics.reportActionBarBackTap();
                onBackPressed();
                break;
        }
        return true;
    }

    @Override
    public void onOpenSourceClicked() {
        mAnalytics.reportOpenSourceTap();
        try {
            Utils.openUrl(this, BuildConfig.REPOSITORY_URL);
        } catch (BrowserNotFoundException e) {
            Timber.e(e, "Error opening link");
            mView.showNoBrowserError();
        }
    }

    @Override
    public void onAttributionsClicked() {
        mAnalytics.reportAttributionsTap();
        AttributionsActivity.launch(this);
    }
}
