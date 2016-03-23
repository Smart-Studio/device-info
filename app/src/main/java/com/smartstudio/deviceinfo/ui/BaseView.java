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

import android.support.annotation.LayoutRes;
import android.view.View;

/**
 * Base interface to be implemented by all views
 **/
public interface BaseView {
    /**
     * Returns the layout resource id to be inflated in the view
     *
     * @return Layout resource id to be inflated
     **/
    @LayoutRes
    int getLayoutResourceId();

    /**
     * Initialises the view (setting default values, setting up needed objects, etc)
     *
     * @param view Android view containing the displayed view hierarchy
     **/
    void init(View view);
}
