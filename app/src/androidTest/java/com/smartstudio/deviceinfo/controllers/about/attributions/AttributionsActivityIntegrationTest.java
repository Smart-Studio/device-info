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


import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;

import com.smartstudio.deviceinfo.R;
import com.smartstudio.deviceinfo.model.Attribution;
import com.smartstudio.deviceinfo.utils.EspressoUtils;

import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

public class AttributionsActivityIntegrationTest {
    @Rule
    public ActivityTestRule<AttributionsActivity> activityRule = new ActivityTestRule<>(AttributionsActivity.class);

    @Test
    public void testAttributions() throws Exception {
        List<Attribution> attributions = activityRule.getActivity().mAttributionsProvider.getAttributions();

        onView(withId(R.id.list_attributions)).check(matches(EspressoUtils.withRecyclerViewChildCount(attributions.size())));

        for (int i = 0; i < attributions.size(); i++) {
            Attribution attribution = attributions.get(i);
            onView(withId(R.id.list_attributions))
                    .perform(RecyclerViewActions.actionOnItemAtPosition(i, EspressoUtils.checkAttributionView(attribution.getLibrary(),
                            attribution.getAuthor(), attribution.getDescription(), attribution.getLicense())));
        }
    }
}
