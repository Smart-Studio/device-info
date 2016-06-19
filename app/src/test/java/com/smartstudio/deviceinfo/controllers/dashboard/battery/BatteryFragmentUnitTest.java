package com.smartstudio.deviceinfo.controllers.dashboard.battery;

import android.view.View;

import com.smartstudio.deviceinfo.BuildConfig;
import com.smartstudio.deviceinfo.logic.dashboard.battery.BatteryStateProvider;
import com.smartstudio.deviceinfo.model.BatteryState;
import com.smartstudio.deviceinfo.robolectric.CustomRobolectricGradleTestRunner;
import com.smartstudio.deviceinfo.ui.dashboard.battery.BatteryView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.support.v4.SupportFragmentTestUtil;

import javax.inject.Inject;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(CustomRobolectricGradleTestRunner.class)
@Config(sdk = 21, constants = BuildConfig.class)
public class BatteryFragmentUnitTest {
    @Inject
    BatteryView mView;
    @Inject
    BatteryStateProvider mProvider;

    private BatteryFragmentForTest mFragment;

    @Before
    public void setUp() throws Exception {
        mFragment = new BatteryFragmentForTest();
        SupportFragmentTestUtil.startFragment(mFragment);
        mFragment.mComponent.inject(this);
    }

    @Test
    public void testOnAttach() throws Exception {
        verify(mProvider).setListener(mFragment);
    }

    @Test
    public void tesOnCreateView() throws Exception {
        verify(mView).getLayoutResourceId();
    }

    @Test
    public void testOnViewCreated() throws Exception {
        verify(mView).init(any(View.class));
    }

    @Test
    public void testOnStart() throws Exception {
        verify(mProvider).requestBatteryStateUpdates();
    }

    @Test
    public void testOnStop() throws Exception {
        mFragment.onStop();
        verify(mProvider).stopBatteryStateUpdates();
    }

    @Test
    public void testOnShareClicked() throws Exception {
        mFragment.onSharedClicked();
        verify(mView).showShareDialog();
    }

    @Test
    public void testOnBatteryStateChanged() throws Exception {
        BatteryState state = mock(BatteryState.class);
        mFragment.onBatteryStateChanged(state);
        verify(mView).showBatteryState(state);
    }
}
