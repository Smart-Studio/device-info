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

package com.smartstudio.deviceinfo.ui.about;

import android.content.res.Resources;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.smartstudio.deviceinfo.BuildConfig;
import com.smartstudio.deviceinfo.R;
import com.smartstudio.deviceinfo.controllers.about.AboutController;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * TODO Add a class header comment
 */
public class AboutViewImpl implements AboutView {
    @Bind(R.id.txt_version)
    TextView mTxtVersion;

    @Bind(R.id.txt_open_source)
    TextView mTxtOpenSource;

    @Bind(R.id.txt_attributions)
    TextView mTxtAttributions;

    private final AboutController mController;

    @Inject
    public AboutViewImpl(AboutController controller) {
        mController = controller;
    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_about;
    }

    @Override
    public void init(View view) {
        ButterKnife.bind(this, view);

        Resources resources = view.getResources();
        Toolbar toolbar = ButterKnife.findById(view, R.id.toolbar_about);
        ActionBar actionBar = mController.setUpToolBar(toolbar);
        actionBar.setDisplayHomeAsUpEnabled(true);
        String version = resources.getString(R.string.about_version, BuildConfig.VERSION_NAME);
        mTxtVersion.setText(version);
        mTxtOpenSource.setOnClickListener(v -> mController.onOpenSourceClicked());
    }
}
