package com.smartstudio.deviceinfo.logic.dashboard.system;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.os.Build;

import com.smartstudio.deviceinfo.logic.dashboard.system.SystemInfoProviderImpl;
import com.smartstudio.deviceinfo.model.SystemInfo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static com.smartstudio.deviceinfo.utils.TestUtils.mockStaticField;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Build.class, System.class, SystemInfoProviderImpl.class})
public class SystemInfoProviderImplTest {
    private static final String ANDROID_VERSION = "4.3";
    private static final int ANDROID_API = 18;
    private static final String OPEN_GL_VERSION = "3.0";
    private static final String BUILD_ID = "LMY47D";
    private static final String ABI = "x86";
    private static final String BOOTLOADER = "BHZ10m";
    private static final String OS_NAME = "Linux 3.10";
    private static final String OS_VERSION = "android-build@vpba27.mtv.corp.google.com";
    private static final String VM_ART_VERSION = "2.1";
    private static final String VM_DALVIK_VERSION = "1.5";

    @Mock
    private Context mMockContext;
    private SystemInfoProviderImpl mProvider;

    @Before
    public void setUp() throws Exception {
        mockStatic(Build.class, System.class);
        mockStaticField(Build.VERSION.class, "RELEASE", ANDROID_VERSION);
        mockStaticField(Build.VERSION.class, "SDK_INT", ANDROID_API);
        mockStaticField(Build.class, "ID", BUILD_ID);
        mockStaticField(Build.class, "CPU_ABI", ABI);
        mockStaticField(Build.class, "BOOTLOADER", BOOTLOADER);

        when(System.getProperty(SystemInfoProviderImpl.OS_NAME)).thenReturn(OS_NAME);
        when(System.getProperty(SystemInfoProviderImpl.OS_VERSION)).thenReturn(OS_VERSION);

        ActivityManager mockActivityManager = mock(ActivityManager.class);
        ConfigurationInfo mockConfigInfo = mock(ConfigurationInfo.class);
        when(mMockContext.getSystemService(Application.ACTIVITY_SERVICE)).thenReturn(mockActivityManager);
        when(mockActivityManager.getDeviceConfigurationInfo()).thenReturn(mockConfigInfo);
        when(mockConfigInfo.getGlEsVersion()).thenReturn(OPEN_GL_VERSION);


        mProvider = new SystemInfoProviderImpl(mMockContext);
    }

    @Test
    public void testGetSystemInfo() throws Exception {
        mockJavaVmProperty(VM_ART_VERSION);

        SystemInfo systemInfo = mProvider.getSystemInfo();
        assertThat(systemInfo.getAndroidVersion()).isEqualTo(ANDROID_VERSION);
        assertThat(systemInfo.getAndroidApi()).isEqualTo(ANDROID_API);
        assertThat(systemInfo.getBuildId()).isEqualTo(BUILD_ID);
        assertThat(systemInfo.getOpenGlVersion()).isEqualTo(OPEN_GL_VERSION);
        assertThat(systemInfo.getAbi()).isEqualTo(ABI);
        assertThat(systemInfo.getBootloader()).isEqualTo(BOOTLOADER);
        assertThat(systemInfo.getKernel()).isEqualTo(OS_NAME + " " + OS_VERSION);
        assertThat(systemInfo.getJavaVm()).isEqualTo(SystemInfoProviderImpl.VM_ART + " " + VM_ART_VERSION);
    }


    @Test
    public void testCodeNames() throws Exception {
        mockJavaVmProperty(VM_ART_VERSION);
        testAndroidCodename(Build.VERSION_CODES.CUPCAKE, SystemInfoProviderImpl.CUPCAKE);
        testAndroidCodename(Build.VERSION_CODES.DONUT, SystemInfoProviderImpl.DONUT);
        testAndroidCodename(Build.VERSION_CODES.ECLAIR, SystemInfoProviderImpl.ECLAIR);
        testAndroidCodename(Build.VERSION_CODES.ECLAIR_0_1, SystemInfoProviderImpl.ECLAIR_0_1);
        testAndroidCodename(Build.VERSION_CODES.ECLAIR_MR1, SystemInfoProviderImpl.ECLAIR_MR1);
        testAndroidCodename(Build.VERSION_CODES.FROYO, SystemInfoProviderImpl.FROYO);
        testAndroidCodename(Build.VERSION_CODES.GINGERBREAD, SystemInfoProviderImpl.GINGERBREAD);
        testAndroidCodename(Build.VERSION_CODES.GINGERBREAD_MR1, SystemInfoProviderImpl.GINGERBREAD_MR1);
        testAndroidCodename(Build.VERSION_CODES.HONEYCOMB, SystemInfoProviderImpl.HONEYCOMB);
        testAndroidCodename(Build.VERSION_CODES.HONEYCOMB_MR1, SystemInfoProviderImpl.HONEYCOMB_MR1);
        testAndroidCodename(Build.VERSION_CODES.HONEYCOMB_MR2, SystemInfoProviderImpl.HONEYCOMB_MR2);
        testAndroidCodename(Build.VERSION_CODES.ICE_CREAM_SANDWICH, SystemInfoProviderImpl.ICE_CREAM_SANDWICH);
        testAndroidCodename(Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1, SystemInfoProviderImpl.ICE_CREAM_SANDWICH_MR1);
        testAndroidCodename(Build.VERSION_CODES.JELLY_BEAN, SystemInfoProviderImpl.JELLY_BEAN);
        testAndroidCodename(Build.VERSION_CODES.JELLY_BEAN_MR1, SystemInfoProviderImpl.JELLY_BEAN_MR1);
        testAndroidCodename(Build.VERSION_CODES.JELLY_BEAN_MR2, SystemInfoProviderImpl.JELLY_BEAN_MR2);
        testAndroidCodename(Build.VERSION_CODES.KITKAT, SystemInfoProviderImpl.KITKAT);
        testAndroidCodename(Build.VERSION_CODES.LOLLIPOP, SystemInfoProviderImpl.LOLLIPOP);
        testAndroidCodename(Build.VERSION_CODES.LOLLIPOP_MR1, SystemInfoProviderImpl.LOLLIPOP_MR1);
        testAndroidCodename(Build.VERSION_CODES.M, SystemInfoProviderImpl.MARSHMALLOW);
        testAndroidCodename(50, SystemInfoProviderImpl.UNKNOWN);
        testAndroidCodename(0, SystemInfoProviderImpl.UNKNOWN);
        testAndroidCodename(-20, SystemInfoProviderImpl.UNKNOWN);
    }

    @Test
    public void testJavaVm() throws Exception {
        testVm(VM_ART_VERSION, SystemInfoProviderImpl.VM_ART);
        testVm(VM_DALVIK_VERSION, SystemInfoProviderImpl.VM_DALVIK);
    }

    private void mockJavaVmProperty(String javaVm) {
        when(System.getProperty(SystemInfoProviderImpl.JAVA_VM)).thenReturn(javaVm);
    }

    private void testAndroidCodename(int sdkInt, String expectedCodename) throws IllegalAccessException {
        mockStaticField(Build.VERSION.class, "SDK_INT", sdkInt);
        SystemInfo systemInfo = mProvider.getSystemInfo();
        assertThat(systemInfo.getAndroidCodename()).isEqualTo(expectedCodename);
    }

    private void testVm(String vmVersion, String expected) {
        mockJavaVmProperty(vmVersion);
        SystemInfo systemInfo = mProvider.getSystemInfo();
        assertThat(systemInfo.getJavaVm()).isEqualTo(expected + " " + vmVersion);
    }
}