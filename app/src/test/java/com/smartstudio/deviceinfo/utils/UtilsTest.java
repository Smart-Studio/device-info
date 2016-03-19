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

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.net.Uri;
import android.util.DisplayMetrics;

import com.smartstudio.deviceinfo.exceptions.BrowserNotFoundException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Resources.class, Utils.class, Uri.class})
public class UtilsTest {
    private static final String METHOD_INTENT_AVAILABLE = "mIsIntentAvailable";
    private static final int PIXELS_EVEN = 4;
    private static final int DPS_EVEN = 2;
    private static final int PIXELS_ODD = 5;
    private static final int DPS_ODD = 3;
    private static final double DENSITY = 2.0;
    private static final int PIXELS_ZERO = 0;
    private static final int DPS_ZERO = 0;
    private static final String OPEN_URL = "http://wwww.google.com";


    @Mock
    private Resources mResources;
    @Mock
    private DisplayMetrics mDisplayMetrics;
    @Mock
    private List<ResolveInfo> mResolveInfoList;

    @Test
    public void testPxToDpEven() throws Exception {
        int result = Utils.pxToDp(PIXELS_EVEN, DENSITY);
        assertThat(result).isEqualTo(DPS_EVEN);
    }

    @Test
    public void testPxToDpOdd() throws Exception {
        int result = Utils.pxToDp(PIXELS_ODD, DENSITY);
        assertThat(result).isEqualTo(DPS_ODD);
    }

    @Test
    public void testPxToDpZero() throws Exception {
        int result = Utils.pxToDp(PIXELS_ZERO, DENSITY);
        assertThat(result).isEqualTo(DPS_ZERO);
    }

    @Test
    public void testOpenUrl() throws Exception {
        Context context = mockContext();
        setupOpenUrlMocks(true);
        Utils.openUrl(context, OPEN_URL);
    }

    @Test(expected = BrowserNotFoundException.class)
    public void testOpenUrlNoBrowser() throws Exception {
        Context context = mockContext();
        setupOpenUrlMocks(false);
        Utils.openUrl(context, OPEN_URL);
    }

    private void setupOpenUrlMocks(boolean isIntentAvailable) {
        mockStatic(Uri.class);
        PowerMockito.stub(PowerMockito.method(Utils.class, METHOD_INTENT_AVAILABLE)).toReturn(isIntentAvailable);
        Uri uri = mock(Uri.class);
        when(Uri.parse(OPEN_URL)).thenReturn(uri);
    }

    @Test
    public void testIsIntentAvailableTrue() throws Exception {
        Context context = mockContext();
        Intent intent = mockIntent();
        PackageManager packageManager = mockPackageManager();
        setupIsIntentAvailableMocks(false, context, packageManager, intent);

        boolean result = isIntentAvailable(context, intent);
        assertThat(result).isTrue();
    }

    @Test
    public void testIsIntentAvailableFalse() throws Exception {
        Context context = mockContext();
        Intent intent = mockIntent();
        PackageManager packageManager = mockPackageManager();
        setupIsIntentAvailableMocks(true, context, packageManager, intent);

        boolean result = isIntentAvailable(context, intent);
        assertThat(result).isFalse();
    }

    private void setupIsIntentAvailableMocks(boolean infoListEmpty, Context context,
                                             PackageManager packageManager, Intent intent) {
        when(context.getPackageManager()).thenReturn(packageManager);
        when(mResolveInfoList.isEmpty()).thenReturn(infoListEmpty);
        when(packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY)).thenReturn(mResolveInfoList);
    }

    private boolean isIntentAvailable(Context context, Intent intent) {
        return Utils.isIntentAvailable(context, intent);
    }

    private Context mockContext() {
        return mock(Context.class);
    }

    private Intent mockIntent() {
        return mock(Intent.class);
    }

    private PackageManager mockPackageManager() {
        return mock(PackageManager.class);
    }
}