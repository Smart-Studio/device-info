package com.smartstudio.deviceinfo.logic.dashboard.screeninfo;

import android.content.Intent;
import android.os.Build;

import com.smartstudio.deviceinfo.R;
import com.smartstudio.deviceinfo.logic.dashboard.ShareManagerImpl;
import com.smartstudio.deviceinfo.logic.dashboard.ShareManagerImplTest;
import com.smartstudio.deviceinfo.model.ScreenInfoViewModel;
import com.smartstudio.deviceinfo.utils.TestUtils;

import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.verifyStatic;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Build.class)
public class ScreenInfoShareManagerImplTest extends ShareManagerImplTest<ScreenInfoViewModel> {
    private static final String STRING_TITLE_DEVICE_INFO = "Device Info";
    private static final String STRING_MODEL = "Model";
    private static final String STRING_MANUFACTURER = "Manufacturer";
    private static final String STRING_TITLE_SCREEN_DENSITY = "Screen density";
    private static final String STRING_DENSITY = "Density";
    private static final String STRING_DENSITY_CODE = "Density code";
    private static final String STRING_DENSITY_X = "Density X";
    private static final String STRING_DENSITY_Y = "Density Y";
    private static final String STRING_TITLE_SCREEN_SIZE = "Screen size";
    private static final String STRING_RESOLUTION = "Resolution";
    private static final String STRING_INCHES = "Inches";
    private static final String STRING_SIZE = "Size";
    private static final String STRING_STATUS_HEIGHT = "Status bar height";
    private static final String STRING_NAV_HEIGHT = "Navigation bar height";
    private static final String STRING_TITLE_CONTENT_AREA = "Content Area";
    private static final String STRING_HEIGHT = "Height";
    private static final String STRING_ACTION_BAR_HEIGHT = "Action bar height";
    private static final String STRING_TOP = "Top";
    private static final String STRING_BOTTOM = "Bottom";
    private static final String STRING_SHARE = "title";


    private static final String DEVICE_MODEL = "Nexus 4";
    private static final String MANUFACTURER = "LGE";
    private static final String DENSITY = "320 dp (x2.0)";
    private static final String DENSITY_CODE = "xhdpi";
    private static final String DENSITY_X = "319.79 dp";
    private static final String DENSITY_Y = "318.74 dp";
    private static final String RESOLUTION = "768x1280 px (384x640 dp)";
    private static final String INCHES = "4.7 \"";
    private static final String SCREEN_SIZE = "normal";
    private static final String STATUS_BAR_HEIGHT = "50 px (25 dp)";
    private static final String NAVIGATION_BAR_HEIGHT = "96 px (48 dp)";
    private static final String HEIGHT = "1134 px (567 dp)";
    private static final String ACTION_BAR_HEIGHT = "96 px (48 dp)";
    private static final String CONTENT_TOP = "146 px (73 dp)";
    private static final String CONTENT_BOTTOM = "1184 px (592 dp)";


    @Mock
    private ScreenInfoViewModel mInfo;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        mockStatic(Build.class);
        TestUtils.mockStaticField(Build.class, "MODEL", DEVICE_MODEL);
    }

    @Override
    protected ShareManagerImpl<ScreenInfoViewModel> buildShareManager() {
        return new ScreenInfoShareManagerImpl(mMockIntent, mMockContext);
    }

    @Override
    public void testShare() throws Exception {
        mockString(R.string.device_info_title, STRING_TITLE_DEVICE_INFO);
        mockString(R.string.model, STRING_MODEL);
        mockString(R.string.manufacturer, STRING_MANUFACTURER);
        mockString(R.string.screen_density_title, STRING_TITLE_SCREEN_DENSITY);
        mockString(R.string.screen_density, STRING_DENSITY);
        mockString(R.string.screen_density_code, STRING_DENSITY_CODE);
        mockString(R.string.screen_density_x, STRING_DENSITY_X);
        mockString(R.string.screen_density_y, STRING_DENSITY_Y);
        mockString(R.string.screen_size_title, STRING_TITLE_SCREEN_SIZE);
        mockString(R.string.screen_resolution, STRING_RESOLUTION);
        mockString(R.string.screen_inches, STRING_INCHES);
        mockString(R.string.screen_size, STRING_SIZE);
        mockString(R.string.status_bar_height, STRING_STATUS_HEIGHT);
        mockString(R.string.navigation_bar_height, STRING_NAV_HEIGHT);
        mockString(R.string.screen_content_title, STRING_TITLE_CONTENT_AREA);
        mockString(R.string.screen_content_height, STRING_HEIGHT);
        mockString(R.string.screen_action_bar_height, STRING_ACTION_BAR_HEIGHT);
        mockString(R.string.screen_content_top, STRING_TOP);
        mockString(R.string.screen_content_bottom, STRING_BOTTOM);
        mockString(R.string.share_screen_info_title, STRING_SHARE);

        when(Intent.createChooser(mMockIntent, STRING_SHARE)).thenReturn(mMockChooserIntent);

        super.testShare();

        verify(mMockIntent).putExtra(Intent.EXTRA_SUBJECT, DEVICE_MODEL);
        String sharedText = addTitle(STRING_TITLE_DEVICE_INFO) + addProperty(STRING_MODEL, DEVICE_MODEL)
                + addProperty(STRING_MANUFACTURER, MANUFACTURER) + addTitle(STRING_TITLE_SCREEN_DENSITY)
                + addProperty(STRING_DENSITY, DENSITY) + addProperty(STRING_DENSITY_CODE, DENSITY_CODE)
                + addProperty(STRING_DENSITY_X, DENSITY_X) + addProperty(STRING_DENSITY_Y, DENSITY_Y)
                + addTitle(STRING_TITLE_SCREEN_SIZE) + addProperty(STRING_RESOLUTION, RESOLUTION)
                + addProperty(STRING_INCHES, INCHES) + addProperty(STRING_SIZE, SCREEN_SIZE)
                + addProperty(STRING_STATUS_HEIGHT, STATUS_BAR_HEIGHT) + addProperty(STRING_NAV_HEIGHT, NAVIGATION_BAR_HEIGHT)
                + addTitle(STRING_TITLE_CONTENT_AREA) + addProperty(STRING_HEIGHT, HEIGHT) + addProperty(STRING_ACTION_BAR_HEIGHT, ACTION_BAR_HEIGHT)
                + addProperty(STRING_TOP, CONTENT_TOP) + addProperty(STRING_BOTTOM, CONTENT_BOTTOM);

        verify(mMockIntent).putExtra(Intent.EXTRA_SUBJECT, DEVICE_MODEL);
        verify(mMockIntent).putExtra(Intent.EXTRA_TEXT, sharedText);
        verifyStatic();
        Intent.createChooser(mMockIntent, STRING_SHARE);
        verify(mMockContext).startActivity(mMockChooserIntent);
    }

    @Override
    protected ScreenInfoViewModel getSharedObject() {
        mockScreenInfo();
        return mInfo;
    }

    private void mockScreenInfo() {
        when(mInfo.getModel()).thenReturn(DEVICE_MODEL);
        when(mInfo.getManufacturer()).thenReturn(MANUFACTURER);
        when(mInfo.getDensity()).thenReturn(DENSITY);
        when(mInfo.getDensityCode()).thenReturn(DENSITY_CODE);
        when(mInfo.getDensityX()).thenReturn(DENSITY_X);
        when(mInfo.getDensityY()).thenReturn(DENSITY_Y);
        when(mInfo.getResolution()).thenReturn(RESOLUTION);
        when(mInfo.getInches()).thenReturn(INCHES);
        when(mInfo.getScreenSize()).thenReturn(SCREEN_SIZE);
        when(mInfo.getStatusBarHeight()).thenReturn(STATUS_BAR_HEIGHT);
        when(mInfo.getNavBarHeight()).thenReturn(NAVIGATION_BAR_HEIGHT);
        when(mInfo.getContentHeight()).thenReturn(HEIGHT);
        when(mInfo.getActionBarHeight()).thenReturn(ACTION_BAR_HEIGHT);
        when(mInfo.getContentTop()).thenReturn(CONTENT_TOP);
        when(mInfo.getContentBottom()).thenReturn(CONTENT_BOTTOM);
    }

    @Override
    protected void testBuildText() {
        verifyString(R.string.device_info_title);
        verifyString(R.string.model);
        verify(mInfo, times(2)).getModel();
        verifyString(R.string.manufacturer);
        verify(mInfo).getManufacturer();
        verifyString(R.string.screen_density_title);
        verifyString(R.string.screen_density);
        verify(mInfo).getDensity();
        verifyString(R.string.screen_density_code);
        verify(mInfo).getDensityCode();
        verifyString(R.string.screen_density_x);
        verify(mInfo).getDensityX();
        verifyString(R.string.screen_density_y);
        verify(mInfo).getDensityY();
        verifyString(R.string.screen_size_title);
        verifyString(R.string.screen_resolution);
        verify(mInfo).getResolution();
        verifyString(R.string.screen_inches);
        verify(mInfo).getInches();
        verifyString(R.string.screen_size);
        verify(mInfo).getScreenSize();
        verifyString(R.string.status_bar_height);
        verify(mInfo).getStatusBarHeight();
        verifyString(R.string.navigation_bar_height);
        verify(mInfo).getNavBarHeight();
        verifyString(R.string.screen_content_title);
        verifyString(R.string.screen_content_height);
        verify(mInfo).getContentHeight();
        verifyString(R.string.screen_action_bar_height);
        verify(mInfo).getActionBarHeight();
        verifyString(R.string.screen_content_top);
        verify(mInfo).getContentTop();
        verifyString(R.string.screen_content_bottom);
        verify(mInfo).getContentBottom();
    }

    @Override
    protected void testGetTitle() {
        verifyString(R.string.share_screen_info_title);
    }


}