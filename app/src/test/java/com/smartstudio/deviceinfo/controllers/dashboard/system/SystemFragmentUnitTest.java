package com.smartstudio.deviceinfo.controllers.dashboard.system;

import android.view.View;

import com.smartstudio.deviceinfo.BuildConfig;
import com.smartstudio.deviceinfo.analytics.dashboard.system.SystemAnalytics;
import com.smartstudio.deviceinfo.injection.qualifiers.ForFabric;
import com.smartstudio.deviceinfo.injection.qualifiers.ForGoogle;
import com.smartstudio.deviceinfo.logic.dashboard.system.SystemInfoProvider;
import com.smartstudio.deviceinfo.model.SystemInfo;
import com.smartstudio.deviceinfo.robolectric.CustomRobolectricGradleTestRunner;
import com.smartstudio.deviceinfo.ui.dashboard.system.SystemView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.support.v4.SupportFragmentTestUtil;

import javax.inject.Inject;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

@RunWith(CustomRobolectricGradleTestRunner.class)
@Config(sdk = 21, constants = BuildConfig.class)
public class SystemFragmentUnitTest {
    @Inject
    SystemView mView;
    @Inject
    SystemInfoProvider mProvider;
    @Inject
    SystemInfo mSystemInfo;
    @Inject
    @ForGoogle
    SystemAnalytics mAnalytics;
    @Inject
    @ForFabric
    SystemAnalytics mFabricAnalytics;

    private SystemFragmentForTest mFragment;

    @Before
    public void setUp() throws Exception {
        mFragment = new SystemFragmentForTest();
        SupportFragmentTestUtil.startFragment(mFragment);
        mFragment.mComponent.inject(this);
    }

    @Test
    public void testOnCreateView() throws Exception {
        verify(mView).getLayoutResourceId();
    }

    @Test
    public void testOnViewCreated() throws Exception {
        verify(mView).init(any(View.class));
        verify(mProvider).getSystemInfo();
        verify(mView).showSystemInfo(mSystemInfo);
    }

    @Test
    public void testOnResume() throws Exception {
        verify(mAnalytics).reportScreen();
        verify(mFabricAnalytics).reportScreen();
    }

    @Test
    public void testOnShareClicked() throws Exception {
        mFragment.onSharedClicked();

        verify(mView).showShareDialog();
        verify(mAnalytics).reportShare();
        verify(mFabricAnalytics).reportShare();
    }
}
