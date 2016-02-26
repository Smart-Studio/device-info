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

package com.smartstudio.deviceinfo.controllers;

import android.support.annotation.IdRes;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;

import com.smartstudio.deviceinfo.R;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.smartstudio.deviceinfo.logic.ScreenInfoManagerImpl.ACTION_BAR_HEIGHT;
import static com.smartstudio.deviceinfo.logic.ScreenInfoManagerImpl.ANDROID_API;
import static com.smartstudio.deviceinfo.logic.ScreenInfoManagerImpl.ANDROID_CODENAME;
import static com.smartstudio.deviceinfo.logic.ScreenInfoManagerImpl.ANDROID_VERSION;
import static com.smartstudio.deviceinfo.logic.ScreenInfoManagerImpl.CONTENT_BOTTOM;
import static com.smartstudio.deviceinfo.logic.ScreenInfoManagerImpl.CONTENT_HEIGHT;
import static com.smartstudio.deviceinfo.logic.ScreenInfoManagerImpl.CONTENT_TOP;
import static com.smartstudio.deviceinfo.logic.ScreenInfoManagerImpl.DENSITY;
import static com.smartstudio.deviceinfo.logic.ScreenInfoManagerImpl.DENSITY_CODE;
import static com.smartstudio.deviceinfo.logic.ScreenInfoManagerImpl.DENSITY_DPI;
import static com.smartstudio.deviceinfo.logic.ScreenInfoManagerImpl.DENSITY_X;
import static com.smartstudio.deviceinfo.logic.ScreenInfoManagerImpl.DENSITY_Y;
import static com.smartstudio.deviceinfo.logic.ScreenInfoManagerImpl.DEVICE_MODEL;
import static com.smartstudio.deviceinfo.logic.ScreenInfoManagerImpl.HEIGHT;
import static com.smartstudio.deviceinfo.logic.ScreenInfoManagerImpl.INCHES;
import static com.smartstudio.deviceinfo.logic.ScreenInfoManagerImpl.MANUFACTURER;
import static com.smartstudio.deviceinfo.logic.ScreenInfoManagerImpl.NAVIGATION_BAR_HEIGHT;
import static com.smartstudio.deviceinfo.logic.ScreenInfoManagerImpl.SCREEN_SIZE;
import static com.smartstudio.deviceinfo.logic.ScreenInfoManagerImpl.STATUS_BAR_HEIGHT;
import static com.smartstudio.deviceinfo.logic.ScreenInfoManagerImpl.WIDTH;
import static org.hamcrest.CoreMatchers.allOf;

public class ScreenInfoActivityTest {
    private static final int WIDTH_DP = 384;
    private static final int HEIGHT_DP = 640;
    private static final int STATUS_HEIGHT_DP = 25;
    private static final int NAVIGATION_BAR_HEIGHT_DP = 48;
    private static final int ACTION_BAR_HEIGHT_DP = 48;
    private static final int CONTENT_TOP_DP = 73;
    private static final int CONTENT_BOTTOM_DP = 592;
    private static final int CONTENT_HEIGHT_DP = 567;

    @Rule
    public ActivityTestRule<ScreenInfoActivity> activityRule = new ActivityTestRule<>(ScreenInfoActivity.class);

    @Test
    public void testProperties() throws Exception {
        assertPropertyText(R.id.view_device_name, DEVICE_MODEL);
        assertPropertyText(R.id.view_device_manufacturer, MANUFACTURER);
        assertPropertyText(R.id.view_os, ANDROID_VERSION);
        assertPropertyText(R.id.view_os_codename, ANDROID_CODENAME);
        assertPropertyText(R.id.view_os_api, String.valueOf(ANDROID_API));
        assertPropertyText(R.id.view_screen_res, WIDTH + "x" + HEIGHT + " px (" + WIDTH_DP + "x" + HEIGHT_DP + " dp)");
        assertPropertyText(R.id.view_screen_inches, INCHES + "\"");
        assertPropertyText(R.id.view_screen_size, SCREEN_SIZE);
        assertPropertyText(R.id.view_screen_status, STATUS_BAR_HEIGHT + " px (" + STATUS_HEIGHT_DP + " dp)");
        assertPropertyText(R.id.view_screen_navigation, NAVIGATION_BAR_HEIGHT + " px (" + NAVIGATION_BAR_HEIGHT_DP + " dp)");
        assertPropertyText(R.id.view_screen_density, DENSITY_DPI + " dp (x" + DENSITY + ")");
        assertPropertyText(R.id.view_screen_density_code, DENSITY_CODE);
        assertPropertyText(R.id.view_screen_density_x, DENSITY_X + " dp");
        assertPropertyText(R.id.view_screen_density_y, DENSITY_Y + " dp");
        assertPropertyText(R.id.view_screen_content_height, CONTENT_HEIGHT + " px (" + CONTENT_HEIGHT_DP + " dp)");
        assertPropertyText(R.id.view_screen_action_bar, ACTION_BAR_HEIGHT + " px (" + ACTION_BAR_HEIGHT_DP + " dp)");
        assertPropertyText(R.id.view_screen_content_top, CONTENT_TOP + " px (" + CONTENT_TOP_DP + " dp)");
        assertPropertyText(R.id.view_screen_content_bottom, CONTENT_BOTTOM + " px (" + CONTENT_BOTTOM_DP + " dp)");
        onView(allOf(withId(R.id.txt_property_value), withParent(withId(R.id.view_screen_navigation))))
                .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }

    private void assertPropertyText(@IdRes int propertyId, String value) {
        onView(allOf(withId(R.id.txt_property_value), withParent(withId(propertyId))))
                .check(matches(withText(value)));
    }
}