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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.smartstudio.deviceinfo.R;
import com.smartstudio.deviceinfo.controllers.about.attributions.AttributionsController;
import com.smartstudio.deviceinfo.model.Attribution;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import java.util.List;

import butterknife.ButterKnife;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.spy;

@RunWith(PowerMockRunner.class)
@PrepareForTest({AttributionsAdapterImpl.class, AttributionViewHolder.class,
        LayoutInflater.class, ButterKnife.class})
public class AttributionsAdapterImplTest {
    private static final int ATTRIBUTION_COUNT = 10;
    private static final int POSITION = 4;
    private static final String REPO_URL = "repo_url";

    @Mock
    private AttributionsController mController;
    @Mock
    private List<Attribution> mAttributions;

    private AttributionsAdapterImpl mAdapter;

    @Before
    public void setUp() throws Exception {
        mAdapter = new AttributionsAdapterImpl(mController);
    }

    @Test
    public void testOnCreateViewHolder() throws Exception {
        mockStatic(LayoutInflater.class, ButterKnife.class);
        Context context = mock(Context.class);
        ViewGroup parent = mock(ViewGroup.class);
        when(parent.getContext()).thenReturn(context);
        LayoutInflater layoutInflater = mock(LayoutInflater.class);
        when(LayoutInflater.from(context)).thenReturn(layoutInflater);
        View view = mock(View.class);
        when(layoutInflater.inflate(R.layout.view_attribution, parent, false)).thenReturn(view);

        AttributionViewHolder holder = mAdapter.onCreateViewHolder(parent, 0);
        verify(view).setOnClickListener(anyObject());
        assertThat(holder.itemView).isEqualTo(view);
    }

    @Test
    public void testOnBindViewHolder() throws Exception {
        Attribution attributionPos0 = mock(Attribution.class);
        Attribution attributionPos3 = mock(Attribution.class);
        when(mAttributions.get(0)).thenReturn(attributionPos0);
        when(mAttributions.get(3)).thenReturn(attributionPos3);
        AttributionViewHolder holder = mock(AttributionViewHolder.class);
        mAdapter = spy(mAdapter);
        doNothing().when(mAdapter).notifyDataSetChanged();

        mAdapter.showAttributions(mAttributions);
        mAdapter.onBindViewHolder(holder, 0);
        verify(mAttributions).get(0);
        verify(holder).onBind(attributionPos0);

        mAdapter.onBindViewHolder(holder, 3);
        verify(mAttributions).get(3);
        verify(holder).onBind(attributionPos3);
    }

    @Test
    public void testGetItemCount() throws Exception {
        assertThat(mAdapter.getItemCount()).isEqualTo(0);
        when(mAttributions.size()).thenReturn(ATTRIBUTION_COUNT);
        mAdapter = spy(mAdapter);
        doNothing().when(mAdapter).notifyDataSetChanged();

        mAdapter.showAttributions(mAttributions);
        assertThat(mAdapter.getItemCount()).isEqualTo(ATTRIBUTION_COUNT);
    }

    @Test
    public void testShowAttributions() throws Exception {
        when(mAttributions.size()).thenReturn(ATTRIBUTION_COUNT);
        mAdapter = spy(mAdapter);
        doNothing().when(mAdapter).notifyDataSetChanged();

        mAdapter.showAttributions(mAttributions);
        assertThat(mAdapter.getItemCount()).isEqualTo(ATTRIBUTION_COUNT);
        verify(mAdapter).notifyDataSetChanged();
    }

    @Test
    public void testOnAttributionClicked() throws Exception {
        AttributionViewHolder viewHolder = mock(AttributionViewHolder.class);
        when(viewHolder.getAdapterPosition()).thenReturn(POSITION);
        Attribution attribution = mock(Attribution.class);
        when(attribution.getRepoUrl()).thenReturn(REPO_URL);
        when(mAttributions.get(POSITION)).thenReturn(attribution);
        Whitebox.setInternalState(mAdapter, "mAttributions", mAttributions);

        Whitebox.invokeMethod(mAdapter, "onAttributionClicked", viewHolder);

        verify(mAttributions).get(POSITION);
        verify(attribution).getRepoUrl();
        verify(mController).onAttributionClicked(REPO_URL);

    }
}