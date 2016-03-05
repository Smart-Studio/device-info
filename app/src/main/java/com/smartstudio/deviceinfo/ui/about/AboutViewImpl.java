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
import android.view.View;
import android.widget.TextView;

import com.smartstudio.deviceinfo.BuildConfig;
import com.smartstudio.deviceinfo.R;
import com.smartstudio.deviceinfo.controllers.about.AboutController;
import com.smartstudio.deviceinfo.ui.BaseViewImpl;

import javax.inject.Inject;

import butterknife.Bind;

/**
 * TODO Add a class header comment
 */
public class AboutViewImpl extends BaseViewImpl implements AboutView {
    @Bind(R.id.txt_about_version)
    TextView mTxtVersion;

    @Bind(R.id.txt_about_open_source)
    TextView mTxtOpenSource;

    @Bind(R.id.txt_about_attributions)
    TextView mTxtAttributions;

    private final AboutController mController;

    @Inject
    public AboutViewImpl(AboutController controller) {
        super(controller);
        mController = controller;
    }

    @Override
    public int getLayoutResourceId() {
        return R.layout.activity_about;
    }

    @Override
    public void init(View view) {
        super.init(view);
        Resources resources = view.getResources();
        mActionBar.setDisplayHomeAsUpEnabled(true);
        String version = resources.getString(R.string.about_version, BuildConfig.VERSION_NAME);
        mTxtVersion.setText(version);
        mTxtOpenSource.setOnClickListener(v -> mController.onOpenSourceClicked());
        mTxtAttributions.setOnClickListener(v -> mController.onAttributionsClicked());
    }

    @Override
    protected int getToolbarId() {
        return R.id.toolbar_about;
    }
}
