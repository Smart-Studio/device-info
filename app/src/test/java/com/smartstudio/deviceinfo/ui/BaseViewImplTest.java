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

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import butterknife.ButterKnife;

import static org.assertj.core.api.Assertions.assertThat;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.verifyStatic;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ButterKnife.class)
public abstract class BaseViewImplTest {
    @Mock
    protected View mMockView;
    protected BaseView mBaseView;

    @Before
    public void setUp() throws Exception {
        mBaseView = getBaseView();
    }

    @Test
    public void testGetLayoutResource() throws Exception {
        assertThat(mBaseView.getLayoutResourceId()).isEqualTo(getLayoutResource());
    }

    @Test
    public void testInit() throws Exception {
        initMocks();
        mBaseView.init(mMockView);

        verifyStatic();
        ButterKnife.bind(mBaseView, mMockView);
    }

    protected void initMocks() {
        mockStatic(ButterKnife.class);
    }

    public abstract BaseView getBaseView();

    @LayoutRes
    public abstract int getLayoutResource();
}
