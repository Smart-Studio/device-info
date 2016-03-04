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

package com.smartstudio.deviceinfo.controllers.screeninfo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.smartstudio.deviceinfo.DeviceInfoApp;
import com.smartstudio.deviceinfo.R;
import com.smartstudio.deviceinfo.controllers.BaseActivity;
import com.smartstudio.deviceinfo.controllers.about.AboutActivity;
import com.smartstudio.deviceinfo.injection.Injector;
import com.smartstudio.deviceinfo.logic.ScreenInfoManager;
import com.smartstudio.deviceinfo.model.ScreenInfo;
import com.smartstudio.deviceinfo.ui.screeninfo.ScreenInfoView;

import javax.inject.Inject;

public class ScreenInfoActivity extends BaseActivity implements ScreenInfoController {
    @Inject
    ScreenInfoView mView;
    @Inject
    ScreenInfoManager mScreenInfoManager;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(mView.getLayoutResource());
        mView.init(getWindow().getDecorView());

        ScreenInfo screenInfo = mScreenInfoManager.getScreenInfo();
        mView.showScreenInfo(screenInfo);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.about:
                AboutActivity.launch(this);
                break;
        }
        return true;
    }

    @Override
    protected void initComponent() {
        DeviceInfoApp.get().getComponent()
                .plus(Injector.provideScreenInfoModule(this, getWindowManager().getDefaultDisplay()))
                .inject(this);
    }

    @Override
    public void setUpToolBar(Toolbar toolbar) {
        toolbar.showOverflowMenu();
        setSupportActionBar(toolbar);
    }
}