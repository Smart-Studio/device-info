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

import android.view.View;
import android.widget.TextView;

import com.smartstudio.deviceinfo.R;
import com.smartstudio.deviceinfo.model.Attribution;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AttributionViewHolderImpl extends AttributionViewHolder {
    @Bind(R.id.txt_attribution_library)
    TextView txtLibrary;

    @Bind(R.id.txt_attribution_author)
    TextView txtAuthor;

    @Bind(R.id.txt_attribution_description)
    TextView txtDescription;

    @Bind(R.id.txt_attribution_license)
    TextView txtLicense;

    @Inject
    public AttributionViewHolderImpl(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void onBind(Attribution attribution) {
        txtLibrary.setText(attribution.getLibrary());
        txtAuthor.setText(attribution.getAuthor());
        txtDescription.setText(attribution.getDescription());
        txtLicense.setText(attribution.getLicense());
    }
}
