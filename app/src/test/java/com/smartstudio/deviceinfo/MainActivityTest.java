package com.smartstudio.deviceinfo;

import android.app.Activity;

import com.smartstudio.deviceinfo.robolectric.CustomRobolectricGradleTestRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.annotation.Config;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(CustomRobolectricGradleTestRunner.class)
@Config(sdk = 21, constants = BuildConfig.class)
public class MainActivityTest {

    @Before
    public void setUp() throws Exception {
        Activity activity = Robolectric.setupActivity(MainActivity.class);
    }

    @Test
    public void testSum() throws Exception {
        int sum = 3 + 4;
        assertThat(sum).isEqualTo(7);
    }
}