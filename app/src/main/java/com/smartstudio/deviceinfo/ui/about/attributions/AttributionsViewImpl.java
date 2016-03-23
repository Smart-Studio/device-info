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

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.smartstudio.deviceinfo.R;
import com.smartstudio.deviceinfo.controllers.about.attributions.AttributionsController;
import com.smartstudio.deviceinfo.injection.qualifiers.ForActivity;
import com.smartstudio.deviceinfo.model.Attribution;
import com.smartstudio.deviceinfo.ui.BaseViewImpl;
import com.smartstudio.deviceinfo.utils.ViewUtils;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;

public class AttributionsViewImpl extends BaseViewImpl implements AttributionsView {
    @Bind(R.id.list_attributions)
    RecyclerView mListAttributions;

    private final AttributionsAdapter mAdapter;
    private final RecyclerView.LayoutManager mLayoutManager;
    private final Context mContext;
    private Toast mToast;

    @Inject
    public AttributionsViewImpl(AttributionsController controller, AttributionsAdapter adapter,
                                RecyclerView.LayoutManager layoutManager, @ForActivity Context context) {
        super(controller);
        mAdapter = adapter;
        mLayoutManager = layoutManager;
        mContext = context;
    }

    @Override
    public int getLayoutResourceId() {
        return R.layout.activity_attributions;
    }

    @Override
    public void init(View view) {
        super.init(view);
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mListAttributions.setLayoutManager(mLayoutManager);
        mListAttributions.setAdapter(mAdapter);
    }

    @Override
    protected int getToolbarId() {
        return R.id.toolbar_attributions;
    }

    @Override
    public void showAttributions(List<Attribution> attributions) {
        mAdapter.showAttributions(attributions);
    }

    @Override
    public void showNoBrowserError() {
        mToast = ViewUtils.showNoBrowserToast(mContext, mToast);
    }
}
