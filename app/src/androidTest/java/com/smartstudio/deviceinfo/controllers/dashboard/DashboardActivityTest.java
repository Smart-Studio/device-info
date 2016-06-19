package com.smartstudio.deviceinfo.controllers.dashboard;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.support.test.espresso.intent.rule.IntentsTestRule;

import com.smartstudio.deviceinfo.R;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;

public class DashboardActivityTest {
    @Rule
    public IntentsTestRule<DashboardActivity> activityRule = new IntentsTestRule<>(DashboardActivity.class);

    @Test
    public void testTabsNavigation() throws Exception {
        onView(withText(R.string.tab_system)).perform(click());
        onView(withText(R.string.tab_battery)).perform(click());
        onView(withText(R.string.tab_screen_info)).perform(click());
        onView(withId(R.id.pager_dashboard)).perform(swipeLeft());
        onView(withId(R.id.pager_dashboard)).perform(swipeLeft());
        onView(withId(R.id.pager_dashboard)).perform(swipeRight());
        onView(withId(R.id.pager_dashboard)).perform(swipeLeft());
        onView(withId(R.id.pager_dashboard)).perform(swipeRight());
        onView(withId(R.id.pager_dashboard)).perform(swipeRight());
    }

    @Test
    public void testShareScreenInfo() throws Exception {
        stubResultIntent();

        onView(withId(R.id.share)).perform(click());

        String shareTitle = activityRule.getActivity().getString(R.string.share_screen_info_title);
        intended(allOf(hasAction(Intent.ACTION_CHOOSER), hasExtra(Intent.EXTRA_TITLE, shareTitle)));
    }

    @Test
    public void testShareSystemInfo() throws Exception {
        stubResultIntent();

        onView(withText(R.string.tab_system)).perform(click());
        onView(withId(R.id.share)).perform(click());

        String shareTitle = activityRule.getActivity().getString(R.string.share_system_title);
        intended(allOf(hasAction(Intent.ACTION_CHOOSER), hasExtra(Intent.EXTRA_TITLE, shareTitle)));
    }

    @Test
    public void testShareBatteryState() throws Exception {
        stubResultIntent();

        onView(withText(R.string.tab_battery)).perform(click());
        onView(withId(R.id.share)).perform(click());

        String shareTitle = activityRule.getActivity().getString(R.string.share_battery_title);
        intended(allOf(hasAction(Intent.ACTION_CHOOSER), hasExtra(Intent.EXTRA_TITLE, shareTitle)));
    }

    private void stubResultIntent() {
        Intent intent = new Intent();
        Instrumentation.ActivityResult result = new Instrumentation.ActivityResult(Activity.RESULT_OK, intent);
        intending(hasAction(Intent.ACTION_CHOOSER)).respondWith(result);
    }
}
