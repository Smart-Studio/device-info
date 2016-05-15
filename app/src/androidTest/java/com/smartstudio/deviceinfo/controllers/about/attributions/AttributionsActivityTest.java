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

package com.smartstudio.deviceinfo.controllers.about.attributions;

import android.content.Intent;
import android.content.res.Resources;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.intent.rule.IntentsTestRule;

import com.smartstudio.deviceinfo.R;
import com.smartstudio.deviceinfo.controllers.about.AboutActivity;
import com.smartstudio.deviceinfo.controllers.dashboard.DashboardActivity;
import com.smartstudio.deviceinfo.utils.EspressoUtils;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasData;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;

public class AttributionsActivityTest {
    @Rule
    public IntentsTestRule<DashboardActivity> activityRule = new IntentsTestRule<>(DashboardActivity.class);

    private Resources mResources;

    @Before
    public void setUp() throws Exception {
        mResources = activityRule.getActivity().getResources();
        navigateToAboutActivity();
        navigateToAttributions();
    }

    @Test
    public void testAttributionsActivityUI() throws Exception {
        String toolbarTitle = mResources.getString(R.string.attributions_title);
        EspressoUtils.matchToolbarTitle(toolbarTitle);

        onView(withId(R.id.list_attributions)).check(matches(EspressoUtils.withRecyclerViewChildCount(10)));

        String description = mResources.getString(R.string.attribution_butter_knife_description);
        checkAttribution(0, "Butter Knife", "Jake Wharton", description, "Apache Version 2.0");

        description = mResources.getString(R.string.attribution_timber_description);
        checkAttribution(1, "Timber", "Jake Wharton", description, "Apache Version 2.0");

        description = mResources.getString(R.string.attribution_leakcanary_description);
        checkAttribution(2, "LeakCanary", "Square", description, "Apache Version 2.0");

        description = mResources.getString(R.string.attribution_dagger_description);
        checkAttribution(3, "Dagger 2", "Google", description, "Apache Version 2.0");

        description = mResources.getString(R.string.attribution_stetho_description);
        checkAttribution(4, "Stetho", "Facebook", description, "BSD License");

        description = mResources.getString(R.string.attribution_mockito_description);
        checkAttribution(5, "Mockito", "", description, "MIT License");

        description = mResources.getString(R.string.attribution_powermock_description);
        checkAttribution(6, "Powermock", "Jayway", description, "Apache Version 2.0");

        description = mResources.getString(R.string.attribution_assertj_android_description);
        checkAttribution(7, "AssertJ Android", "Square", description, "Apache Version 2.0");

        description = mResources.getString(R.string.attribution_assertj_description);
        checkAttribution(8, "AssertJ", "", description, "Apache Version 2.0");

        description = mResources.getString(R.string.attribution_robolectric_description);
        checkAttribution(9, "Robolectric", "Pivotal Labs", description, "Apache Version 2.0");
    }

    @Test
    public void testOpenLinkButterKnife() throws Exception {
        checkOpenedLink(0, "https://github.com/JakeWharton/butterknife");
    }

    @Test
    public void testOpenLinkTimber() throws Exception {
        checkOpenedLink(1, "https://github.com/JakeWharton/timber");
    }

    @Test
    public void testOpenLinkLeakCanary() throws Exception {
        checkOpenedLink(2, "https://github.com/square/leakcanary");
    }

    @Test
    public void testOpenLinkDagger2() throws Exception {
        checkOpenedLink(3, "https://github.com/google/dagger");
    }

    @Test
    public void testOpenLinkStetho() throws Exception {
        checkOpenedLink(4, "https://github.com/facebook/stetho");
    }

    @Test
    public void testOpenLinkMockito() throws Exception {
        checkOpenedLink(5, "https://github.com/mockito/mockito");
    }

    @Test
    public void testOpenLinkPowerMock() throws Exception {
        checkOpenedLink(6, "https://github.com/jayway/powermock");
    }

    @Test
    public void testOpenLinkAssertJAndroid() throws Exception {
        checkOpenedLink(7, "https://github.com/square/assertj-android");
    }

    @Test
    public void testOpenLinkAssertJ() throws Exception {
        checkOpenedLink(8, "https://github.com/joel-costigliola/assertj-core");
    }

    @Test
    public void testOpenLinkRobolectric() throws Exception {
        checkOpenedLink(9, "https://github.com/robolectric/robolectric");
    }

    private void navigateToAboutActivity() {
        openActionBarOverflowOrOptionsMenu(activityRule.getActivity());
        onView(withText(R.string.about_title)).perform(click());
        intended(hasComponent(AboutActivity.class.getName()));
    }

    private void navigateToAttributions() {
        onView(withId(R.id.txt_about_attributions)).perform(click());
        intended(hasComponent(AttributionsActivity.class.getName()));
    }

    private void checkAttribution(int position, String library, String author, String description, String license) {
        onView(withId(R.id.list_attributions)).perform(RecyclerViewActions.actionOnItemAtPosition(position,
                EspressoUtils.checkAttributionView(library, author, description, license)));
    }

    private void checkOpenedLink(int position, String link) {
        onView(withId(R.id.list_attributions)).perform(RecyclerViewActions.actionOnItemAtPosition(position, click()));
        intended(allOf(hasAction(Intent.ACTION_VIEW), hasData(link)));
    }
}
