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

import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import com.smartstudio.deviceinfo.controllers.ToolbarController;

import org.junit.Test;
import org.mockito.Mock;

import butterknife.ButterKnife;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public abstract class ActionBarViewImplTest extends BaseViewImplTest {
    @Mock
    private Toolbar mToolbar;
    @Mock
    protected ActionBar mActionBar;

    @Test
    public void testInit() throws Exception {
        super.testInit();
        verify(getBaseController()).setUpToolbar(mToolbar);
    }

    public abstract ToolbarController getBaseController();

    @Override
    protected void initMocks() {
        super.initMocks();
        when(ButterKnife.findById(eq(mMockView), anyInt())).thenReturn(mToolbar);
        ToolbarController controller = getBaseController();
        when(controller.setUpToolbar(mToolbar)).thenReturn(mActionBar);
    }
}