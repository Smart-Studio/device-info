package com.smartstudio.deviceinfo.controllers.dashboard.system;

import android.support.annotation.IdRes;
import android.support.test.rule.ActivityTestRule;

import com.smartstudio.deviceinfo.R;
import com.smartstudio.deviceinfo.controllers.dashboard.DashboardActivity;
import com.smartstudio.deviceinfo.logic.dashboard.system.SystemInfoProviderImpl;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;

public class SystemFragmentTest {

    @Rule
    public ActivityTestRule<DashboardActivity> activityRule = new ActivityTestRule<>(DashboardActivity.class);

    @Test
    public void testProperties() throws Exception {
        assertPropertyText(R.id.view_os, SystemInfoProviderImpl.ANDROID_VERSION);
        assertPropertyText(R.id.view_os_codename, SystemInfoProviderImpl.ICE_CREAM_SANDWICH);
        assertPropertyText(R.id.view_os_api, String.valueOf(SystemInfoProviderImpl.ANDROID_API));
        assertPropertyText(R.id.view_java_vm, SystemInfoProviderImpl.JAVA_VM);
        assertPropertyText(R.id.view_abi, SystemInfoProviderImpl.CPU_ABI);
        assertPropertyText(R.id.view_bootloader, SystemInfoProviderImpl.BOOTLOADER);
        assertPropertyText(R.id.view_build_id, SystemInfoProviderImpl.BUILD_ID);
        assertPropertyText(R.id.view_kernel, SystemInfoProviderImpl.KERNEL);
    }

    private void assertPropertyText(@IdRes int propertyId, String value) {
        onView(allOf(withId(R.id.txt_property_value), withParent(withId(propertyId))))
                .check(matches(withText(value)));
    }
}
