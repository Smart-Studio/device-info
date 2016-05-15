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


import android.view.View;

import org.junit.Test;
import org.mockito.Mock;

import butterknife.ButterKnife;

import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.verifyStatic;

public abstract class BaseViewImplTest {
    @Mock
    protected View mView;

    @Test
    public void testInit() throws Exception {
        initMocks();
        BaseView baseView = getBaseView();
        baseView.init(mView);

        verifyStatic();
        ButterKnife.bind(baseView, mView);
    }

    public abstract BaseView getBaseView();

    protected void initMocks(){
        mockStatic(ButterKnife.class);
    }
}
