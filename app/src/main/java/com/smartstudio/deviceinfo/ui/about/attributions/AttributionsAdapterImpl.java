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

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.smartstudio.deviceinfo.R;
import com.smartstudio.deviceinfo.controllers.about.attributions.AttributionsController;
import com.smartstudio.deviceinfo.model.Attribution;

import java.util.List;

import javax.inject.Inject;

public class AttributionsAdapterImpl extends AttributionsAdapter {
    private final AttributionsController mController;

    private List<Attribution> mAttributions;
    private int mCount;

    @Inject
    public AttributionsAdapterImpl(AttributionsController controller) {
        mController = controller;
    }

    @Override
    public AttributionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.view_attribution, parent, false);
        AttributionViewHolder viewHolder = new AttributionViewHolderImpl(view);
        view.setOnClickListener(v -> onAttributionClicked(viewHolder));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AttributionViewHolder holder, int position) {
        holder.onBind(mAttributions.get(position));
    }

    @Override
    public int getItemCount() {
        return mCount;
    }

    @Override
    public void showAttributions(@NonNull List<Attribution> attributions) {
        mAttributions = attributions;
        mCount = attributions.size();
        notifyDataSetChanged();
    }


    private void onAttributionClicked(AttributionViewHolder viewHolder) {
        int pos = viewHolder.getAdapterPosition();
        String repoUrl = mAttributions.get(pos).getRepoUrl();
        mController.onAttributionClicked(repoUrl);
    }
}
