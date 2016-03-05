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

package com.smartstudio.deviceinfo.controllers.about;

import android.content.res.Resources;
import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;

import com.smartstudio.deviceinfo.BuildConfig;
import com.smartstudio.deviceinfo.R;
import com.smartstudio.deviceinfo.controllers.screeninfo.ScreenInfoActivity;
import com.smartstudio.deviceinfo.utils.EspressoUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class AboutActivityTest {
    private Resources mResources;

    @Rule
    public ActivityTestRule<ScreenInfoActivity> activityRule = new ActivityTestRule<>(ScreenInfoActivity.class);

    @Before
    public void setUp() throws Exception {
        mResources = activityRule.getActivity().getResources();
        Intents.init();}

    @Test
    public void testAboutActivity() throws Exception {
        navigateToAboutActivity();
        String toolbarTitle = mResources.getString(R.string.about_title);
        EspressoUtils.matchToolbarTitle(toolbarTitle);
        String version = mResources.getString(R.string.about_version, BuildConfig.VERSION_NAME);
        onView(withId(R.id.txt_about_version)).check(matches(withText(version)));
        onView(withId(R.id.txt_about_open_source)).check(matches(withText(R.string.about_open_source)));
        onView(withId(R.id.txt_about_open_source)).check(matches(EspressoUtils.withCompoundDrawable(R.mipmap.ic_github_icon)));
        onView(withId(R.id.txt_about_attributions)).check(matches(withText(R.string.attributions_title)));
    }

    @Test
    public void testAboutActivityPressBack() throws Exception {
        navigateToAboutActivity();
        pressBack();
        checkMainToolbarTitle();
    }

    @Test
    public void testAboutActivityNavigateUp() throws Exception {
        navigateToAboutActivity();
        onView(withContentDescription("Navigate up")).perform(click());
        checkMainToolbarTitle();
    }

    @After
    public void tearDown() throws Exception {
        Intents.release();
    }

    private void navigateToAboutActivity() {
        openActionBarOverflowOrOptionsMenu(activityRule.getActivity());
        onView(withText(R.string.about_title)).perform(click());
        intended(hasComponent(AboutActivity.class.getName()));
    }

    private void checkMainToolbarTitle() {
        String toolbarTitle = mResources.getString(R.string.device_info_title);
        EspressoUtils.matchToolbarTitle(toolbarTitle);
    }
}
