package com.smartstudio.deviceinfo.logic.dashboard.system;

import android.content.Intent;
import android.os.Build;

import com.smartstudio.deviceinfo.R;
import com.smartstudio.deviceinfo.logic.dashboard.ShareManagerImpl;
import com.smartstudio.deviceinfo.logic.dashboard.ShareManagerImplTest;
import com.smartstudio.deviceinfo.model.SystemInfo;
import com.smartstudio.deviceinfo.utils.TestUtils;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.verifyStatic;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Build.class)
public class SystemInfoShareManagerImplTest extends ShareManagerImplTest<SystemInfo> {
    private static final String STRING_ANDROID = "Android";
    private static final String STRING_VERSION = "version";
    private static final String STRING_CODENAME = "codename";
    private static final String STRING_API = "api";
    private static final String STRING_JAVA_VM = "java_vm";
    private static final String STRING_OTHERS = "others";
    private static final String STRING_ABI = "abi";
    private static final String STRING_BOOTLOADER = "bootloader";
    private static final String STRING_OPEN_GL = "open gl";
    private static final String STRING_BUILD_ID = "build id";
    private static final String STRING_KERNEL = "kernel";
    private static final String STRING_SHARE = "title";


    private static final String MODEL = "Nexus 6";
    private static final String ANDROID_VERSION = "4.0";
    private static final String ICE_CREAM_SANDWICH = "Ice Cream Sandwich";
    private static final int ANDROID_API = 15;
    private static final String JAVA_VM = "Art 2.0";
    private static final String CPU_ABI = "x86";
    private static final String BOOTLOADER = "BHZ10m";
    private static final String OPEN_GL = "2.0";
    private static final String BUILD_ID = "MRA58K";
    private static final String KERNEL = "Linux 3.10.0";

    @Mock
    private SystemInfo mInfo;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        mockStatic(Build.class);
        TestUtils.mockStaticField(Build.class, "MODEL", MODEL);
    }

    @Override
    protected ShareManagerImpl<SystemInfo> buildShareManager() {
        return new SystemInfoShareManagerImpl(mMockIntent, mMockContext);
    }

    @Override
    public void testShare() throws Exception {
        mockString(R.string.share_screen_info_title, STRING_SHARE);
        mockString(R.string.android_title, STRING_ANDROID);
        mockString(R.string.os_version, STRING_VERSION);
        mockString(R.string.os_codename, STRING_CODENAME);
        mockString(R.string.os_api, STRING_API);
        mockString(R.string.java_vm, STRING_JAVA_VM);
        mockString(R.string.others, STRING_OTHERS);
        mockString(R.string.abi, STRING_ABI);
        mockString(R.string.bootloader, STRING_BOOTLOADER);
        mockString(R.string.opengl, STRING_OPEN_GL);
        mockString(R.string.build_id, STRING_BUILD_ID);
        mockString(R.string.kernel, STRING_KERNEL);
        mockString(R.string.share_system_title, STRING_SHARE);

        when(Intent.createChooser(mMockIntent, STRING_SHARE)).thenReturn(mMockChooserIntent);

        super.testShare();

        verify(mMockIntent).putExtra(Intent.EXTRA_SUBJECT, MODEL);
        String sharedText = addTitle(STRING_ANDROID) + addProperty(STRING_VERSION, ANDROID_VERSION)
                + addProperty(STRING_CODENAME, ICE_CREAM_SANDWICH) + addProperty(STRING_API, String.valueOf(ANDROID_API))
                + addProperty(STRING_JAVA_VM, JAVA_VM) + addProperty(STRING_BUILD_ID, BUILD_ID)
                + addTitle(STRING_OTHERS) + addProperty(STRING_OPEN_GL, OPEN_GL)
                + addProperty(STRING_ABI, CPU_ABI) + addProperty(STRING_BOOTLOADER, BOOTLOADER)
                + addProperty(STRING_KERNEL, KERNEL);

        verify(mMockIntent).putExtra(Intent.EXTRA_SUBJECT, MODEL);
        verify(mMockIntent).putExtra(Intent.EXTRA_TEXT, sharedText);
        verifyStatic();
        Intent.createChooser(mMockIntent, STRING_SHARE);
        verify(mMockContext).startActivity(mMockChooserIntent);
    }

    @Override
    protected SystemInfo getSharedObject() {
        mockSystemInfo();
        return mInfo;
    }

    private void mockSystemInfo() {
        when(mInfo.getAndroidVersion()).thenReturn(ANDROID_VERSION);
        when(mInfo.getAndroidCodename()).thenReturn(ICE_CREAM_SANDWICH);
        when(mInfo.getAndroidApi()).thenReturn(ANDROID_API);
        when(mInfo.getJavaVm()).thenReturn(JAVA_VM);
        when(mInfo.getAbi()).thenReturn(CPU_ABI);
        when(mInfo.getBootloader()).thenReturn(BOOTLOADER);
        when(mInfo.getOpenGlVersion()).thenReturn(OPEN_GL);
        when(mInfo.getBuildId()).thenReturn(BUILD_ID);
        when(mInfo.getKernel()).thenReturn(KERNEL);
    }

    @Override
    protected void testBuildText() {
        verifyString(R.string.android_title);
        verifyString(R.string.os_version);
        verify(mInfo).getAndroidVersion();
        verifyString(R.string.os_api);
        verify(mInfo).getAndroidApi();
        verifyString(R.string.java_vm);
        verify(mInfo).getJavaVm();
        verifyString(R.string.abi);
        verify(mInfo).getAbi();
        verifyString(R.string.bootloader);
        verify(mInfo).getBootloader();
        verifyString(R.string.opengl);
        verify(mInfo).getOpenGlVersion();
        verifyString(R.string.build_id);
        verify(mInfo).getBuildId();
        verifyString(R.string.kernel);
        verify(mInfo).getKernel();
    }

    @Override
    protected void testGetTitle() {
        verifyString(R.string.share_system_title);
    }
}