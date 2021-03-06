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

package com.smartstudio.deviceinfo.ui.about.attributions;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.smartstudio.deviceinfo.model.Attribution;

/**
 * {@link RecyclerView.ViewHolder} for an attribution
 **/
public abstract class AttributionViewHolder extends RecyclerView.ViewHolder {

    public AttributionViewHolder(View itemView) {
        super(itemView);
    }

    /**
     * Binds the attributions data to the viewholder view
     *
     * @param attribution Attribution to be displayed on the view
     **/
    public abstract void onBind(Attribution attribution);
}
