package com.smartstudio.deviceinfo.controllers.dashboard.battery;

import android.support.annotation.IdRes;
import android.support.test.rule.ActivityTestRule;

import com.smartstudio.deviceinfo.R;
import com.smartstudio.deviceinfo.controllers.dashboard.DashboardActivity;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;

public class BatteryFragmentTest {
    private static final String LEVEL = "59 %";
    private static final String HEALTH = "Dead";
    private static final String POWER_SOURCE = "AC Charger";
    private static final String STATUS = "Charging";
    private static final String CAPACITY = "2569 mAh";
    private static final String TYPE = "Plutonium";
    private static final String TEMPERATURE = "-142.0 ℃";
    private static final String VOLTAGE = "293 mV";

    @Rule
    public ActivityTestRule<DashboardActivity> activityRule = new ActivityTestRule<>(DashboardActivity.class);

    @Test
    public void testProperties() throws Exception {
        onView(withText(R.string.tab_battery)).perform(click());

        assertPropertyText(R.id.view_battery_level, LEVEL);
        assertPropertyText(R.id.view_battery_health, HEALTH);
        assertPropertyText(R.id.view_battery_source, POWER_SOURCE);
        assertPropertyText(R.id.view_battery_status, STATUS);
        assertPropertyText(R.id.view_battery_capacity, CAPACITY);
        assertPropertyText(R.id.view_battery_type, TYPE);
        assertPropertyText(R.id.view_battery_temperature, TEMPERATURE);
        assertPropertyText(R.id.view_battery_voltage, VOLTAGE);
    }

    private void assertPropertyText(@IdRes int propertyId, String value) {
        onView(allOf(withId(R.id.txt_property_value), withParent(withId(propertyId))))
                .check(matches(withText(value)));
    }
}
