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

package com.smartstudio.deviceinfo.logic;

import android.content.res.Resources;

import com.smartstudio.deviceinfo.R;
import com.smartstudio.deviceinfo.model.Attribution;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class AttributionsProviderImpl implements AttributionsProvider {
    private static final String APACHE_LICENSE = "Apache Version 2.0";

    private final Resources mResources;

    @Inject
    public AttributionsProviderImpl(Resources resources) {
        mResources = resources;
    }

    @Override
    public List<Attribution> getAttributions() {
        List<Attribution> attributions = new ArrayList<>();
        String description = mResources.getString(R.string.attribution_butter_knife_description);
        addAttribution("Butter Knife", "Jake Wharton", description, APACHE_LICENSE, "https://github.com/JakeWharton/butterknife", attributions);
        description = mResources.getString(R.string.attribution_timber_description);
        addAttribution("Timber", "Jake Wharton", description, APACHE_LICENSE, "https://github.com/JakeWharton/timber", attributions);
        description = mResources.getString(R.string.attribution_leakcanary_description);
        addAttribution("LeakCanary", "Square", description, APACHE_LICENSE, "https://github.com/square/leakcanary", attributions);
        description = mResources.getString(R.string.attribution_timber_description);
        addAttribution("Dagger 2", "Square", description, APACHE_LICENSE, "https://github.com/google/dagger", attributions);
        description = mResources.getString(R.string.attribution_stetho_description);
        addAttribution("Stetho", "Facebook", description, "BSD License", "https://github.com/facebook/stetho", attributions);
        description = mResources.getString(R.string.attribution_mockito_description);
        addAttribution("Mockito", "", description, "MIT License", "https://github.com/mockito/mockito", attributions);
        description = mResources.getString(R.string.attribution_powermock_description);
        addAttribution("Powermock", "Jayway", description, APACHE_LICENSE, "https://github.com/jayway/powermock", attributions);
        description = mResources.getString(R.string.attribution_assertj_android_description);
        addAttribution("AssertJ Android", "Square", description, APACHE_LICENSE, "https://github.com/square/assertj-android", attributions);
        description = mResources.getString(R.string.attribution_assertj_description);
        addAttribution("AssertJ", "", description, APACHE_LICENSE, "https://github.com/joel-costigliola/assertj-core", attributions);
        description = mResources.getString(R.string.attribution_robolectric_description);
        addAttribution("Robolectric", "Pivotal Labs", description, APACHE_LICENSE, "https://github.com/robolectric/robolectric", attributions);
        return attributions;
    }

    private void addAttribution(String library, String author, String description,
                                String license, String url, List<Attribution> attributions) {
        Attribution attribution = new Attribution(library, author, description, license, url);
        attributions.add(attribution);
    }
}
