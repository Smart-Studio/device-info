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

import com.smartstudio.deviceinfo.controllers.BaseController;
import com.smartstudio.deviceinfo.model.Attribution;

/**
 * Controller for the attributions screen
 **/
public interface AttributionsController extends BaseController {
    /**
     * Called when an attributions is clicked
     *
     * @param attribution Attribution clicked
     **/
    void onAttributionClicked(Attribution attribution);
}
