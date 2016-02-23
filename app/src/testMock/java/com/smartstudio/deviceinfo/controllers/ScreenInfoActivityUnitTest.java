package com.smartstudio.deviceinfo.controllers;

import android.widget.TextView;

import com.smartstudio.deviceinfo.BuildConfig;
import com.smartstudio.deviceinfo.R;
import com.smartstudio.deviceinfo.robolectric.CustomRobolectricGradleTestRunner;
import com.smartstudio.deviceinfo.ui.PropertyLayout;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.annotation.Config;

import butterknife.ButterKnife;

import static org.assertj.android.api.Assertions.assertThat;


@RunWith(CustomRobolectricGradleTestRunner.class)
@Config(sdk = 21, constants = BuildConfig.class)
public class ScreenInfoActivityUnitTest {
    private ScreenInfoActivity mActivity;

    @Before
    public void setUp() throws Exception {
        mActivity = Robolectric.setupActivity(ScreenInfoActivity.class);
    }

    @Test
    public void testSum() throws Exception {
        PropertyLayout viewDeviceModel = ButterKnife.findById(mActivity.getWindow().getDecorView(), R.id.view_device_name);
        TextView txtModel = (TextView) viewDeviceModel.getChildAt(1);
        assertThat(txtModel).containsText("Nexus 4");
    }
}