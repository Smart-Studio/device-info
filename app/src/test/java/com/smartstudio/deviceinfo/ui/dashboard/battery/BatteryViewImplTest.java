package com.smartstudio.deviceinfo.ui.dashboard.battery;

import android.content.res.Resources;
import android.os.BatteryManager;

import com.smartstudio.deviceinfo.R;
import com.smartstudio.deviceinfo.logic.dashboard.battery.BatteryShareManager;
import com.smartstudio.deviceinfo.model.BatteryState;
import com.smartstudio.deviceinfo.model.BatteryViewModel;
import com.smartstudio.deviceinfo.ui.BaseView;
import com.smartstudio.deviceinfo.ui.BaseViewImplTest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static com.smartstudio.deviceinfo.utils.TestUtils.mockPropertyLayout;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class BatteryViewImplTest extends BaseViewImplTest {
    private static final int LEVEL = 59;
    private static final int HEALTH = BatteryManager.BATTERY_HEALTH_DEAD;
    private static final int POWER_SOURCE = BatteryManager.BATTERY_PLUGGED_AC;
    private static final int STATUS = BatteryManager.BATTERY_STATUS_NOT_CHARGING;
    private static final double CAPACITY = 2569.23;
    private static final int CAPACITY_EXPECTED = 2569;
    private static final String TYPE = "Plutonium";
    private static final double TEMPERATURE = -142.52;
    private static final String TEMPERATURE_EXPECTED = "-142.5";
    private static final int VOLTAGE = 293;


    private static final String HEALTH_GOOD = "Good";
    private static final String HEALTH_DEAD = "Dead";
    private static final String HEALTH_COLD = "Cold";
    private static final String HEALTH_OVERHEAT = "Overheat";
    private static final String HEALTH_OVER_VOLTAGE = "Over voltage";
    private static final String HEALTH_UNSPECIFIED = "unspecified";
    private static final String HEALTH_UNKNOWN = "unknown";


    private static final String SOURCE_BATTERY = "Battery";
    private static final String SOURCE_USB = "USB";
    private static final String SOURCE_AC = "AC";
    private static final String SOURCE_WIRELESS = "Wireless";
    private static final String SOURCE_UNKNOWN = "Unknown";

    private static final String STATUS_CHARGING = "Charging";
    private static final String STATUS_DISCHARGING = "Discharging";
    private static final String STATUS_FULL = "Full";
    private static final String STATUS_NOT_CHARGING = "Not charging";
    private static final String STATUS_UNKNOWN = "Unknown";

    private static final double CAPACITY_ROUND_UP = 23.6;
    private static final int CAPACITY_ROUND_UP_EXPECTED = 24;
    private static final double CAPACITY_ROUND_UP_FIVE = 28.5;
    private static final int CAPACITY_ROUND_UP_FIVE_EXPECTED = 29;
    private static final double CAPACITY_ROUND_DOWN = 120.4;
    private static final int CAPACITY_ROUND_DOWN_EXPECTED = 120;

    private static final double TEMPERATURE_ROUND_UP = 23.97;
    private static final double TEMPERATURE_ROUND_UP_EXPECTED = 24;
    private static final double TEMPERATURE_ROUND_UP_FIVE = 28.55;
    private static final double TEMPERATURE_ROUND_UP_FIVE_EXPECTED = 28.6;
    private static final double TEMPERATURE_ROUND_DOWN = 120.42;
    private static final double TEMPERATURE_ROUND_DOWN_EXPECTED = 120.4;

    private static final String PERCENTAGE = " %";
    private static final String UNIT_CAPACITY = " mAh";
    private static final String UNIT_TEMPERATURE = " ℃";
    private static final String UNIT_VOLTAGE = " mV";


    @Mock
    private BatteryShareManager mMockShareManager;
    @Mock
    private Resources mMockResources;
    @Mock
    private BatteryViewModel mMockBatteryViewModel;

    private BatteryViewImpl mView;

    @Override
    @Before
    public void setUp() throws Exception {
        mView = new BatteryViewImpl(mMockShareManager, mMockResources, mMockBatteryViewModel);
        super.setUp();
        mockViews();
    }

    @Test
    public void testShowBatteryState() throws Exception {
        BatteryState state = mockBatteryState();
        when(mMockResources.getString(R.string.battery_health_dead)).thenReturn(HEALTH_DEAD);
        when(mMockResources.getString(R.string.battery_source_battery)).thenReturn(SOURCE_BATTERY);
        when(mMockResources.getString(R.string.battery_status_not_charging)).thenReturn(STATUS_NOT_CHARGING);

        mView.showBatteryState(state);

        verify(mMockResources).getString(R.string.battery_health_dead);
        verify(mMockResources).getString(R.string.battery_source_battery);
        verify(mMockResources).getString(R.string.battery_status_not_charging);
        verifyViewMocks();
    }

    @Test
    public void testHealthGood() throws Exception {
        BatteryState state = mock(BatteryState.class);
        when(state.getHealth()).thenReturn(BatteryManager.BATTERY_HEALTH_GOOD);
        when(mMockResources.getString(R.string.battery_health_good)).thenReturn(HEALTH_GOOD);

        mView.showBatteryState(state);

        verify(mMockResources).getString(R.string.battery_health_good);
        verify(mMockBatteryViewModel).setHealth(HEALTH_GOOD);
        verify(mView.mViewHealth).setValue(HEALTH_GOOD);
    }

    @Test
    public void testHealthDead() throws Exception {
        BatteryState state = mock(BatteryState.class);
        when(state.getHealth()).thenReturn(BatteryManager.BATTERY_HEALTH_DEAD);
        when(mMockResources.getString(R.string.battery_health_dead)).thenReturn(HEALTH_DEAD);

        mView.showBatteryState(state);

        verify(mMockResources).getString(R.string.battery_health_dead);
        verify(mMockBatteryViewModel).setHealth(HEALTH_DEAD);
        verify(mView.mViewHealth).setValue(HEALTH_DEAD);
    }

    @Test
    public void testHealthCold() throws Exception {
        BatteryState state = mock(BatteryState.class);
        when(state.getHealth()).thenReturn(BatteryManager.BATTERY_HEALTH_COLD);
        when(mMockResources.getString(R.string.battery_health_cold)).thenReturn(HEALTH_COLD);

        mView.showBatteryState(state);

        verify(mMockResources).getString(R.string.battery_health_cold);
        verify(mMockBatteryViewModel).setHealth(HEALTH_COLD);
        verify(mView.mViewHealth).setValue(HEALTH_COLD);
    }

    @Test
    public void testHealthOverheat() throws Exception {
        BatteryState state = mock(BatteryState.class);
        when(state.getHealth()).thenReturn(BatteryManager.BATTERY_HEALTH_OVERHEAT);
        when(mMockResources.getString(R.string.battery_health_overheat)).thenReturn(HEALTH_OVERHEAT);

        mView.showBatteryState(state);

        verify(mMockResources).getString(R.string.battery_health_overheat);
        verify(mMockBatteryViewModel).setHealth(HEALTH_OVERHEAT);
        verify(mView.mViewHealth).setValue(HEALTH_OVERHEAT);
    }

    @Test
    public void testHealthOverVoltage() throws Exception {
        BatteryState state = mock(BatteryState.class);
        when(state.getHealth()).thenReturn(BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE);
        when(mMockResources.getString(R.string.battery_health_over_voltage)).thenReturn(HEALTH_OVER_VOLTAGE);

        mView.showBatteryState(state);

        verify(mMockResources).getString(R.string.battery_health_over_voltage);
        verify(mMockBatteryViewModel).setHealth(HEALTH_OVER_VOLTAGE);
        verify(mView.mViewHealth).setValue(HEALTH_OVER_VOLTAGE);
    }

    @Test
    public void testHealthUnspecified() throws Exception {
        BatteryState state = mock(BatteryState.class);
        when(state.getHealth()).thenReturn(BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE);
        when(mMockResources.getString(R.string.battery_health_unspecified)).thenReturn(HEALTH_UNSPECIFIED);

        mView.showBatteryState(state);

        verify(mMockResources).getString(R.string.battery_health_unspecified);
        verify(mMockBatteryViewModel).setHealth(HEALTH_UNSPECIFIED);
        verify(mView.mViewHealth).setValue(HEALTH_UNSPECIFIED);
    }

    @Test
    public void testHealthUnknown() throws Exception {
        BatteryState state = mock(BatteryState.class);
        when(state.getHealth()).thenReturn(BatteryManager.BATTERY_HEALTH_UNKNOWN);
        when(mMockResources.getString(R.string.battery_health_unknown)).thenReturn(HEALTH_UNKNOWN);

        mView.showBatteryState(state);

        verify(mMockResources).getString(R.string.battery_health_unknown);
        verify(mMockBatteryViewModel).setHealth(HEALTH_UNKNOWN);
        verify(mView.mViewHealth).setValue(HEALTH_UNKNOWN);
    }

    @Test
    public void testSourceBattery() throws Exception {
        BatteryState state = mock(BatteryState.class);
        when(state.isCharging()).thenReturn(false);
        when(mMockResources.getString(R.string.battery_source_battery)).thenReturn(SOURCE_BATTERY);

        mView.showBatteryState(state);

        verify(mMockResources).getString(R.string.battery_source_battery);
        verify(mMockBatteryViewModel).setPowerSource(SOURCE_BATTERY);
        verify(mView.mViewSource).setValue(SOURCE_BATTERY);
    }

    @Test
    public void testSourcePluggedUSB() throws Exception {
        BatteryState state = mock(BatteryState.class);
        when(state.isCharging()).thenReturn(true);
        when(state.getSource()).thenReturn(BatteryManager.BATTERY_PLUGGED_USB);
        when(mMockResources.getString(R.string.battery_source_usb)).thenReturn(SOURCE_USB);

        mView.showBatteryState(state);

        verify(mMockResources).getString(R.string.battery_source_usb);
        verify(mMockBatteryViewModel).setPowerSource(SOURCE_USB);
        verify(mView.mViewSource).setValue(SOURCE_USB);
    }

    @Test
    public void testSourcePluggedAC() throws Exception {
        BatteryState state = mock(BatteryState.class);
        when(state.isCharging()).thenReturn(true);
        when(state.getSource()).thenReturn(BatteryManager.BATTERY_PLUGGED_AC);
        when(mMockResources.getString(R.string.battery_source_ac)).thenReturn(SOURCE_AC);

        mView.showBatteryState(state);

        verify(mMockResources).getString(R.string.battery_source_ac);
        verify(mMockBatteryViewModel).setPowerSource(SOURCE_AC);
        verify(mView.mViewSource).setValue(SOURCE_AC);
    }

    @Test
    public void testSourceWireless() throws Exception {
        BatteryState state = mock(BatteryState.class);
        when(state.isCharging()).thenReturn(true);
        when(state.getSource()).thenReturn(BatteryManager.BATTERY_PLUGGED_WIRELESS);
        when(mMockResources.getString(R.string.battery_source_wireless)).thenReturn(SOURCE_WIRELESS);

        mView.showBatteryState(state);

        verify(mMockResources).getString(R.string.battery_source_wireless);
        verify(mMockBatteryViewModel).setPowerSource(SOURCE_WIRELESS);
        verify(mView.mViewSource).setValue(SOURCE_WIRELESS);
    }

    @Test
    public void testSourceUnknown() throws Exception {
        BatteryState state = mock(BatteryState.class);
        when(state.isCharging()).thenReturn(true);
        when(state.getSource()).thenReturn(-1);
        when(mMockResources.getString(R.string.battery_source_unknown)).thenReturn(SOURCE_UNKNOWN);

        mView.showBatteryState(state);

        verify(mMockResources).getString(R.string.battery_source_unknown);
        verify(mMockBatteryViewModel).setPowerSource(SOURCE_UNKNOWN);
        verify(mView.mViewSource).setValue(SOURCE_UNKNOWN);
    }

    @Test
    public void testStatusCharging() throws Exception {
        BatteryState state = mock(BatteryState.class);
        when(state.getStatus()).thenReturn(BatteryManager.BATTERY_STATUS_CHARGING);
        when(mMockResources.getString(R.string.battery_status_charging)).thenReturn(STATUS_CHARGING);

        mView.showBatteryState(state);

        verify(mMockResources).getString(R.string.battery_status_charging);
        verify(mMockBatteryViewModel).setStatus(STATUS_CHARGING);
        verify(mView.mViewStatus).setValue(STATUS_CHARGING);
    }

    @Test
    public void testStatusDischarging() throws Exception {
        BatteryState state = mock(BatteryState.class);
        when(state.getStatus()).thenReturn(BatteryManager.BATTERY_STATUS_DISCHARGING);
        when(mMockResources.getString(R.string.battery_status_discharging)).thenReturn(STATUS_DISCHARGING);

        mView.showBatteryState(state);

        verify(mMockResources).getString(R.string.battery_status_discharging);
        verify(mMockBatteryViewModel).setStatus(STATUS_DISCHARGING);
        verify(mView.mViewStatus).setValue(STATUS_DISCHARGING);
    }

    @Test
    public void testStatusFull() throws Exception {
        BatteryState state = mock(BatteryState.class);
        when(state.getStatus()).thenReturn(BatteryManager.BATTERY_STATUS_FULL);
        when(mMockResources.getString(R.string.battery_status_full)).thenReturn(STATUS_FULL);

        mView.showBatteryState(state);

        verify(mMockResources).getString(R.string.battery_status_full);
        verify(mMockBatteryViewModel).setStatus(STATUS_FULL);
        verify(mView.mViewStatus).setValue(STATUS_FULL);
    }

    @Test
    public void testStatusNotCharging() throws Exception {
        BatteryState state = mock(BatteryState.class);
        when(state.getStatus()).thenReturn(BatteryManager.BATTERY_STATUS_NOT_CHARGING);
        when(mMockResources.getString(R.string.battery_status_not_charging)).thenReturn(STATUS_NOT_CHARGING);

        mView.showBatteryState(state);

        verify(mMockResources).getString(R.string.battery_status_not_charging);
        verify(mMockBatteryViewModel).setStatus(STATUS_NOT_CHARGING);
        verify(mView.mViewStatus).setValue(STATUS_NOT_CHARGING);
    }

    @Test
    public void testStatusUnknown() throws Exception {
        BatteryState state = mock(BatteryState.class);
        when(state.getStatus()).thenReturn(BatteryManager.BATTERY_STATUS_UNKNOWN);
        when(mMockResources.getString(R.string.battery_status_unknown)).thenReturn(STATUS_UNKNOWN);

        mView.showBatteryState(state);

        verify(mMockResources).getString(R.string.battery_status_unknown);
        verify(mMockBatteryViewModel).setStatus(STATUS_UNKNOWN);
        verify(mView.mViewStatus).setValue(STATUS_UNKNOWN);
    }

    @Test
    public void testCapacityRoundUp() throws Exception {
        BatteryState state = mock(BatteryState.class);
        when(state.getCapacity()).thenReturn(CAPACITY_ROUND_UP);

        mView.showBatteryState(state);

        verify(mMockBatteryViewModel).setCapacity(CAPACITY_ROUND_UP_EXPECTED + UNIT_CAPACITY);
        verify(mView.mViewCapacity).setValue(CAPACITY_ROUND_UP_EXPECTED + UNIT_CAPACITY);
    }

    @Test
    public void testCapacityRoundUpFive() throws Exception {
        BatteryState state = mock(BatteryState.class);
        when(state.getCapacity()).thenReturn(CAPACITY_ROUND_UP_FIVE);

        mView.showBatteryState(state);

        verify(mMockBatteryViewModel).setCapacity(CAPACITY_ROUND_UP_FIVE_EXPECTED + UNIT_CAPACITY);
        verify(mView.mViewCapacity).setValue(CAPACITY_ROUND_UP_FIVE_EXPECTED + UNIT_CAPACITY);
    }

    @Test
    public void testCapacityRoundDown() throws Exception {
        BatteryState state = mock(BatteryState.class);
        when(state.getCapacity()).thenReturn(CAPACITY_ROUND_DOWN);

        mView.showBatteryState(state);

        verify(mMockBatteryViewModel).setCapacity(CAPACITY_ROUND_DOWN_EXPECTED + UNIT_CAPACITY);
        verify(mView.mViewCapacity).setValue(CAPACITY_ROUND_DOWN_EXPECTED + UNIT_CAPACITY);
    }

    @Test
    public void testTemperatureRoundUp() throws Exception {
        BatteryState state = mock(BatteryState.class);
        when(state.getTemperature()).thenReturn(TEMPERATURE_ROUND_UP);

        mView.showBatteryState(state);

        verify(mMockBatteryViewModel).setTemperature(TEMPERATURE_ROUND_UP_EXPECTED + UNIT_TEMPERATURE);
        verify(mView.mViewTemperature).setValue(TEMPERATURE_ROUND_UP_EXPECTED + UNIT_TEMPERATURE);
    }

    @Test
    public void testTemperatureRoundUpFive() throws Exception {
        BatteryState state = mock(BatteryState.class);
        when(state.getTemperature()).thenReturn(TEMPERATURE_ROUND_UP_FIVE);

        mView.showBatteryState(state);

        verify(mMockBatteryViewModel).setTemperature(TEMPERATURE_ROUND_UP_FIVE_EXPECTED + UNIT_TEMPERATURE);
        verify(mView.mViewTemperature).setValue(TEMPERATURE_ROUND_UP_FIVE_EXPECTED + UNIT_TEMPERATURE);
    }

    @Test
    public void testTemperatureRoundDown() throws Exception {
        BatteryState state = mock(BatteryState.class);
        when(state.getTemperature()).thenReturn(TEMPERATURE_ROUND_DOWN);

        mView.showBatteryState(state);

        verify(mMockBatteryViewModel).setTemperature(TEMPERATURE_ROUND_DOWN_EXPECTED + UNIT_TEMPERATURE);
        verify(mView.mViewTemperature).setValue(TEMPERATURE_ROUND_DOWN_EXPECTED + UNIT_TEMPERATURE);
    }

    @Test
    public void testShowShareDialog() throws Exception {
        mView.showShareDialog();

        verify(mMockShareManager).share(mMockBatteryViewModel);
    }

    @Override
    public BaseView getBaseView() {
        return mView;
    }

    @Override
    public int getLayoutResource() {
        return R.layout.fragment_battery;
    }

    private void mockViews() {
        mView.mViewLevel = mockPropertyLayout();
        mView.mViewHealth = mockPropertyLayout();
        mView.mViewSource = mockPropertyLayout();
        mView.mViewStatus = mockPropertyLayout();
        mView.mViewCapacity = mockPropertyLayout();
        mView.mViewType = mockPropertyLayout();
        mView.mViewTemperature = mockPropertyLayout();
        mView.mViewVoltage = mockPropertyLayout();
    }

    private void verifyViewMocks() {
        verify(mMockBatteryViewModel).setLevel(LEVEL + PERCENTAGE);
        verify(mView.mViewLevel).setValue(LEVEL + PERCENTAGE);
        verify(mMockBatteryViewModel).setHealth(HEALTH_DEAD);
        verify(mView.mViewHealth).setValue(HEALTH_DEAD);
        verify(mMockBatteryViewModel).setPowerSource(SOURCE_BATTERY);
        verify(mView.mViewSource).setValue(SOURCE_BATTERY);
        verify(mMockBatteryViewModel).setStatus(STATUS_NOT_CHARGING);
        verify(mView.mViewStatus).setValue(STATUS_NOT_CHARGING);
        verify(mMockBatteryViewModel).setCapacity(CAPACITY_EXPECTED + UNIT_CAPACITY);
        verify(mView.mViewCapacity).setValue(CAPACITY_EXPECTED + UNIT_CAPACITY);
        verify(mMockBatteryViewModel).setType(TYPE);
        verify(mView.mViewType).setValue(TYPE);
        verify(mMockBatteryViewModel).setTemperature(TEMPERATURE_EXPECTED + UNIT_TEMPERATURE);
        verify(mView.mViewTemperature).setValue(TEMPERATURE_EXPECTED + UNIT_TEMPERATURE);
        verify(mMockBatteryViewModel).setVoltage(VOLTAGE + UNIT_VOLTAGE);
        verify(mView.mViewVoltage).setValue(VOLTAGE + UNIT_VOLTAGE);
    }

    private BatteryState mockBatteryState() {
        BatteryState state = mock(BatteryState.class);
        when(state.getLevel()).thenReturn(LEVEL);
        when(state.getHealth()).thenReturn(HEALTH);
        when(state.getSource()).thenReturn(POWER_SOURCE);
        when(state.getStatus()).thenReturn(STATUS);
        when(state.getCapacity()).thenReturn(CAPACITY);
        when(state.getType()).thenReturn(TYPE);
        when(state.getTemperature()).thenReturn(TEMPERATURE);
        when(state.getVoltage()).thenReturn(VOLTAGE);
        return state;
    }
}