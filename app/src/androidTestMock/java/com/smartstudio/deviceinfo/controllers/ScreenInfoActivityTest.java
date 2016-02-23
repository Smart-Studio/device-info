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
import android.support.test.rule.ActivityTestRule;

import com.smartstudio.deviceinfo.R;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;

public class ScreenInfoActivityTest {
    @Rule
    public ActivityTestRule<ScreenInfoActivity> activityRule = new ActivityTestRule<>(ScreenInfoActivity.class);

    @Test
    public void testProperties() throws Exception {
        assertPropertyText(R.id.view_device_name, "Nexus 4");
        assertPropertyText(R.id.view_device_manufacturer, "LGE");
        assertPropertyText(R.id.view_os, "4.3");
        assertPropertyText(R.id.view_os_codename, "Jelly Bean MR2");
        assertPropertyText(R.id.view_os_api, "18");
        assertPropertyText(R.id.view_screen_res, "768x1280 px (384x640 dp)");
        assertPropertyText(R.id.view_screen_inches, "4.7\"");
        assertPropertyText(R.id.view_screen_size, "normal");
        assertPropertyText(R.id.view_screen_status, "50 px (25 dp)");
        assertPropertyText(R.id.view_screen_navigation, "96 px (48 dp)");
        assertPropertyText(R.id.view_screen_density, "320 dp (x2.0)");
        assertPropertyText(R.id.view_screen_density_code, "xhdpi");
        assertPropertyText(R.id.view_screen_density_x, "319.79 dp");
        assertPropertyText(R.id.view_screen_density_y, "318.74 dp");
        assertPropertyText(R.id.view_screen_content_height, "1134 px (567 dp)");
        assertPropertyText(R.id.view_screen_action_bar, "96 px (48 dp)");
        assertPropertyText(R.id.view_screen_content_top, "146 px (73 dp)");
        assertPropertyText(R.id.view_screen_content_bottom, "1184 px (592 dp)");
    }

    private void assertPropertyText(@IdRes int propertyId, String value) {
        onView(allOf(withId(R.id.txt_property_value), withParent(withId(propertyId))))
                .check(matches(withText(value)));
    }
}
