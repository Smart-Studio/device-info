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

package com.smartstudio.deviceinfo.controllers;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.smartstudio.deviceinfo.injection.qualifiers.ForActivity;
import com.smartstudio.deviceinfo.ui.BaseView;

import javax.inject.Inject;

public abstract class BaseActivity extends AppCompatActivity implements ToolbarController {
    @Inject
    @ForActivity
    BaseView mView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        initComponent();
        super.onCreate(savedInstanceState);
        setContentView(mView.getLayoutResourceId());
        mView.init(getWindow().getDecorView());
    }

    protected abstract void initComponent();

    @Override
    public ActionBar setUpToolbar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        return getSupportActionBar();
    }
}
