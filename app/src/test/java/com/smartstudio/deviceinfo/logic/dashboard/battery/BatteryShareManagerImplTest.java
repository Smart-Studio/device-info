package com.smartstudio.deviceinfo.logic.dashboard.battery;


import android.content.Intent;
import android.os.Build;

import com.smartstudio.deviceinfo.R;
import com.smartstudio.deviceinfo.logic.dashboard.ShareManagerImpl;
import com.smartstudio.deviceinfo.logic.dashboard.ShareManagerImplTest;
import com.smartstudio.deviceinfo.model.BatteryViewModel;
import com.smartstudio.deviceinfo.utils.TestUtils;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.verifyStatic;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Build.class)
public class BatteryShareManagerImplTest extends ShareManagerImplTest<BatteryViewModel> {
    private static final String STRING_BATTERY = "Battery";
    private static final String STRING_LEVEL = "Level";
    private static final String STRING_HEALTH = "Health";
    private static final String STRING_POWER_SOURCE = "Power Source";
    private static final String STRING_STATUS = "Status";
    private static final String STRING_CAPACITY = "Capacity";
    private static final String STRING_TYPE = "Type";
    private static final String STRING_TEMPERATURE = "Temperature";
    private static final String STRING_VOLTAGE = "Voltage";
    private static final String STRING_SHARE_TITLE = "title";
    private static final String STRING_SHARE_SUBJECT = "subject";

    private static final String MODEL = "Nexus 6";
    private static final String LEVEL = "55 %";
    private static final String HEALTH = "Dead";
    private static final String POWER_SOURCE = "AC Charger";
    private static final String STATUS = "Discharging";
    private static final String CAPACITY = "1550 mAh";
    private static final String TYPE = "Uranium";
    private static final String TEMPERATURE = "34.5 ℃";
    private static final String VOLTAGE = "10042 mV";

    @Mock
    private BatteryViewModel mBatteryState;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        mockStatic(Build.class);
        TestUtils.mockStaticField(Build.class, "MODEL", MODEL);
    }

    @Override
    protected ShareManagerImpl<BatteryViewModel> buildShareManager() {
        return new BatteryShareManagerImpl(mMockIntent, mMockContext);
    }

    @Override
    public void testShare() throws Exception {
        mockString(R.string.tab_battery, STRING_BATTERY);
        mockString(R.string.battery_level, STRING_LEVEL);
        mockString(R.string.battery_health, STRING_HEALTH);
        mockString(R.string.battery_source, STRING_POWER_SOURCE);
        mockString(R.string.battery_status, STRING_STATUS);
        mockString(R.string.battery_capacity, STRING_CAPACITY);
        mockString(R.string.battery_type, STRING_TYPE);
        mockString(R.string.battery_temperature, STRING_TEMPERATURE);
        mockString(R.string.battery_voltage, STRING_VOLTAGE);
        mockString(R.string.share_battery_title, STRING_SHARE_TITLE);
        mockString(R.string.share_battery_subject, STRING_SHARE_SUBJECT);

        Mockito.when(Intent.createChooser(mMockIntent, STRING_SHARE_TITLE)).thenReturn(mMockChooserIntent);

        super.testShare();

        String sharedText = addTitle(STRING_BATTERY) + addProperty(STRING_LEVEL, LEVEL)
                + addProperty(STRING_HEALTH, HEALTH) + addProperty(STRING_POWER_SOURCE, POWER_SOURCE)
                + addProperty(STRING_STATUS, STATUS) + addProperty(STRING_CAPACITY, CAPACITY)
                + addProperty(STRING_TYPE, TYPE) + addProperty(STRING_TEMPERATURE, TEMPERATURE)
                + addProperty(STRING_VOLTAGE, VOLTAGE);

        verify(mMockIntent).putExtra(Intent.EXTRA_SUBJECT, MODEL + " " + STRING_SHARE_SUBJECT);
        verify(mMockIntent).putExtra(Intent.EXTRA_TEXT, sharedText);
        verifyStatic();
        Intent.createChooser(mMockIntent, STRING_SHARE_TITLE);
        verify(mMockContext).startActivity(mMockChooserIntent);
    }

    @Override
    protected BatteryViewModel getSharedObject() {
        mockBatteryState();
        return mBatteryState;
    }

    private void mockBatteryState() {
        when(mBatteryState.getLevel()).thenReturn(LEVEL);
        when(mBatteryState.getHealth()).thenReturn(HEALTH);
        when(mBatteryState.getPowerSource()).thenReturn(POWER_SOURCE);
        when(mBatteryState.getStatus()).thenReturn(STATUS);
        when(mBatteryState.getCapacity()).thenReturn(CAPACITY);
        when(mBatteryState.getType()).thenReturn(TYPE);
        when(mBatteryState.getTemperature()).thenReturn(TEMPERATURE);
        when(mBatteryState.getVoltage()).thenReturn(VOLTAGE);
    }

    @Override
    protected void testBuildText() {
        verifyString(R.string.tab_battery);
        verifyString(R.string.battery_level);
        verifyString(R.string.battery_health);
        verifyString(R.string.battery_source);
        verifyString(R.string.battery_status);
        verifyString(R.string.battery_capacity);
        verifyString(R.string.battery_type);
        verifyString(R.string.battery_temperature);
        verifyString(R.string.battery_voltage);
    }

    @Override
    protected void testGetTitle() {
        verifyString(R.string.share_battery_title);
    }
}
