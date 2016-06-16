package com.smartstudio.deviceinfo.logic.dashboard.battery;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;

import com.smartstudio.deviceinfo.model.BatteryState;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.powermock.reflect.Whitebox;

import static com.smartstudio.deviceinfo.logic.dashboard.battery.BatteryStateProviderImpl.INVALID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

@RunWith(MockitoJUnitRunner.class)
public class BatteryStateProviderImplTest {
    private static final int LEVEL = 59;
    private static final int HEALTH = BatteryManager.BATTERY_HEALTH_DEAD;
    private static final int POWER_SOURCE = BatteryManager.BATTERY_PLUGGED_AC;
    private static final int STATUS = BatteryManager.BATTERY_STATUS_NOT_CHARGING;
    private static final String TYPE = "Plutonium";
    private static final int TEMPERATURE = 365;
    private static final double TEMPERATURE_EXPECTED = 36.5;
    private static final int VOLTAGE = 293;

    @Mock
    private Context mMockContext;
    @Mock
    private IntentFilter mMockIntentFilter;
    @Mock
    private BatteryState mMockBatteryState;

    private BatteryStateProviderImpl mProvider;

    @Before
    public void setUp() throws Exception {
        mProvider = new BatteryStateProviderImpl(mMockContext, mMockIntentFilter, mMockBatteryState);
        mockStatic(Class.class);
    }

    @Test
    public void testConstructor() throws Exception {
        verify(mMockIntentFilter).addAction(Intent.ACTION_BATTERY_CHANGED);
    }

    @Test
    public void testRequestBatteryUpdates() throws Exception {
        mProvider.requestBatteryUpdates();
        verify(mMockContext).registerReceiver(mProvider, mMockIntentFilter);
    }

    @Test
    public void testStopBatteryUpdates() throws Exception {
        mProvider.stopBatteryUpdates();
        verify(mMockContext).unregisterReceiver(mProvider);
    }

    @Test
    public void testSetListener() throws Exception {
        BatteryStateProviderListener listener = mock(BatteryStateProviderListener.class);
        mProvider.setListener(listener);
        assertThat(listener).isEqualTo(Whitebox.getInternalState(mProvider, "mListener"));
    }

    @Test
    public void testOnReceive() throws Exception {
        BatteryStateProviderListener mockListener = mock(BatteryStateProviderListener.class);
        mProvider.setListener(mockListener);
        Intent mockIntent = mock(Intent.class);
        when(mockIntent.getIntExtra(BatteryManager.EXTRA_LEVEL, INVALID)).thenReturn(LEVEL);
        when(mockIntent.getIntExtra(BatteryManager.EXTRA_HEALTH, INVALID)).thenReturn(HEALTH);
        when(mockIntent.getIntExtra(BatteryManager.EXTRA_STATUS, INVALID)).thenReturn(STATUS);
        when(mockIntent.getIntExtra(BatteryManager.EXTRA_PLUGGED, INVALID)).thenReturn(POWER_SOURCE);
        when(mockIntent.getStringExtra(BatteryManager.EXTRA_TECHNOLOGY)).thenReturn(TYPE);
        when(mockIntent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, INVALID)).thenReturn(TEMPERATURE);
        when(mockIntent.getIntExtra(BatteryManager.EXTRA_VOLTAGE, INVALID)).thenReturn(VOLTAGE);

        mProvider.onReceive(mMockContext, mockIntent);

        verify(mMockBatteryState).setLevel(LEVEL);
        verify(mMockBatteryState).setHealth(HEALTH);
        verify(mMockBatteryState).setCharging(false);
        verify(mMockBatteryState).setStatus(STATUS);
        verify(mMockBatteryState).setSource(POWER_SOURCE);
        verify(mMockBatteryState).setCapacity(INVALID);
        verify(mMockBatteryState).setType(TYPE);
        verify(mMockBatteryState).setTemperature(TEMPERATURE_EXPECTED);
        verify(mMockBatteryState).setVoltage(VOLTAGE);
        verify(mockListener).onBatteryStateChanged(mMockBatteryState);
    }

    @Test
    public void testOnReceiveShouldChargeWhenStatusCharging() throws Exception {
        BatteryStateProviderListener mockListener = mock(BatteryStateProviderListener.class);
        mProvider.setListener(mockListener);
        Intent mockIntent = mock(Intent.class);
        when(mockIntent.getIntExtra(BatteryManager.EXTRA_STATUS, INVALID)).thenReturn(BatteryManager.BATTERY_STATUS_CHARGING);

        mProvider.onReceive(mMockContext, mockIntent);

        verify(mMockBatteryState).setCharging(true);
    }

    @Test
    public void testOnReceiveShouldChargeWhenStatusFull() throws Exception {
        BatteryStateProviderListener mockListener = mock(BatteryStateProviderListener.class);
        mProvider.setListener(mockListener);
        Intent mockIntent = mock(Intent.class);
        when(mockIntent.getIntExtra(BatteryManager.EXTRA_STATUS, INVALID)).thenReturn(BatteryManager.BATTERY_STATUS_FULL);

        mProvider.onReceive(mMockContext, mockIntent);

        verify(mMockBatteryState).setCharging(true);
    }

    @Test
    public void testOnReceiveShouldNotChargeWhenStatusDischarging() throws Exception {
        BatteryStateProviderListener mockListener = mock(BatteryStateProviderListener.class);
        mProvider.setListener(mockListener);
        Intent mockIntent = mock(Intent.class);
        when(mockIntent.getIntExtra(BatteryManager.EXTRA_STATUS, INVALID)).thenReturn(BatteryManager.BATTERY_STATUS_DISCHARGING);

        mProvider.onReceive(mMockContext, mockIntent);

        verify(mMockBatteryState).setCharging(false);
    }

    @Test
    public void testOnReceiveShouldNotChargeWhenStatusNotCharging() throws Exception {
        BatteryStateProviderListener mockListener = mock(BatteryStateProviderListener.class);
        mProvider.setListener(mockListener);
        Intent mockIntent = mock(Intent.class);
        when(mockIntent.getIntExtra(BatteryManager.EXTRA_STATUS, INVALID)).thenReturn(BatteryManager.BATTERY_STATUS_NOT_CHARGING);

        mProvider.onReceive(mMockContext, mockIntent);

        verify(mMockBatteryState).setCharging(false);
    }

    @Test
    public void testOnReceiveShouldNotChargeWhenStatusUnknown() throws Exception {
        BatteryStateProviderListener mockListener = mock(BatteryStateProviderListener.class);
        mProvider.setListener(mockListener);
        Intent mockIntent = mock(Intent.class);
        when(mockIntent.getIntExtra(BatteryManager.EXTRA_STATUS, INVALID)).thenReturn(BatteryManager.BATTERY_STATUS_UNKNOWN);

        mProvider.onReceive(mMockContext, mockIntent);

        verify(mMockBatteryState).setCharging(false);
    }
}