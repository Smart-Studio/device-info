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

package com.smartstudio.deviceinfo.ui;


import android.support.annotation.IdRes;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.smartstudio.deviceinfo.controllers.ToolbarController;

import butterknife.ButterKnife;

public abstract class BaseActionBarView extends BaseViewImpl implements BaseView {
    private final ToolbarController mController;

    protected ActionBar mActionBar;

    public BaseActionBarView(ToolbarController controller) {
        mController = controller;
    }

    @Override
    public void init(View view) {
        super.init(view);
        Toolbar toolbar = ButterKnife.findById(view, getToolbarId());
        mActionBar = mController.setUpToolbar(toolbar);
    }

    @IdRes
    protected abstract int getToolbarId();
}
