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

package com.smartstudio.deviceinfo.utils;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Resources.class)
public class UtilsTest {
    private static final int PIXELS_EVEN = 4;
    private static final int DPS_EVEN = 2;
    private static final int PIXELS_ODD = 5;
    private static final int DPS_ODD = 3;
    private static final float DENSITY = 2.0f;
    private static final int PIXELS_ZERO = 0;
    private static final int DPS_ZERO = 0;

    @Mock
    Resources mResources;
    @Mock
    DisplayMetrics mDisplayMetrics;

    @Before
    public void setUp() throws Exception {
        mockStatic(Resources.class);
        when(mResources.getDisplayMetrics()).thenReturn(mDisplayMetrics);
        mDisplayMetrics.density = DENSITY;
        when(Resources.getSystem()).thenReturn(mResources);
    }

    @Test
    public void testPxToDp() throws Exception {
        int result = Utils.pxToDp(PIXELS_EVEN);
        assertThat(result).isEqualTo(DPS_EVEN);

        result = Utils.pxToDp(PIXELS_ODD);
        assertThat(result).isEqualTo(DPS_ODD);

        result = Utils.pxToDp(PIXELS_ZERO);
        assertThat(result).isEqualTo(DPS_ZERO);
    }
}