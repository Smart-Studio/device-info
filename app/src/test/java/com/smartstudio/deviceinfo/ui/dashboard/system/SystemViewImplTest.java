package com.smartstudio.deviceinfo.ui.dashboard.system;

import com.smartstudio.deviceinfo.R;
import com.smartstudio.deviceinfo.model.SystemInfo;
import com.smartstudio.deviceinfo.ui.BaseView;
import com.smartstudio.deviceinfo.ui.BaseViewImplTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import butterknife.ButterKnife;

import static com.smartstudio.deviceinfo.utils.TestUtils.mockPropertyLayout;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ButterKnife.class)
public class SystemViewImplTest extends BaseViewImplTest {
    private static final String ANDROID_VERSION = "5.1";
    private static final String ANDROID_CODENAME = "Lollipop MR1";
    private static final int ANDROID_API = 22;
    private static final String JAVA_VM = "Art 2.1.0";
    private static final String BUILD_ID = "LMY47D";
    private static final String OPEN_GL = "3.0";
    private static final String ABI = "x86";
    private static final String BOOTLOADER = "BHZ10m";
    private static final String KERNEL = "Linux 3.10";

    private SystemViewImpl mView;

    @Before
    public void setUp() throws Exception {
        mView = new SystemViewImpl();
        super.setUp();
        mockViews();
    }

    @Override
    public int getLayoutResource() {
        return R.layout.fragment_system;
    }

    @Override
    public BaseView getBaseView() {
        return mView;
    }

    @Test
    public void testShowSystemInfo() throws Exception {
        SystemInfo info = mockSystemInfo();
        mView.showSystemInfo(info);
        verifyViewMocks();
    }

    private void mockViews() {
        mView.mViewOs = mockPropertyLayout();
        mView.mViewOsCodename = mockPropertyLayout();
        mView.mViewOsApi = mockPropertyLayout();
        mView.mViewJavaVm = mockPropertyLayout();
        mView.mViewBuildId = mockPropertyLayout();
        mView.mViewOpenGL = mockPropertyLayout();
        mView.mViewAbi = mockPropertyLayout();
        mView.mViewBootloader = mockPropertyLayout();
        mView.mViewKernel = mockPropertyLayout();
    }

    private void verifyViewMocks() {
        verify(mView.mViewOs).setValue(ANDROID_VERSION);
        verify(mView.mViewOsCodename).setValue(ANDROID_CODENAME);
        verify(mView.mViewOsApi).setValue(String.valueOf(ANDROID_API));
        verify(mView.mViewJavaVm).setValue(JAVA_VM);
        verify(mView.mViewBuildId).setValue(BUILD_ID);
        verify(mView.mViewOpenGL).setValue(OPEN_GL);
        verify(mView.mViewAbi).setValue(ABI);
        verify(mView.mViewBootloader).setValue(BOOTLOADER);
        verify(mView.mViewKernel).setValue(KERNEL);
    }

    private SystemInfo mockSystemInfo() {
        SystemInfo info = mock(SystemInfo.class);
        when(info.getAndroidVersion()).thenReturn(ANDROID_VERSION);
        when(info.getAndroidCodename()).thenReturn(ANDROID_CODENAME);
        when(info.getAndroidApi()).thenReturn(ANDROID_API);
        when(info.getJavaVm()).thenReturn(JAVA_VM);
        when(info.getBuildId()).thenReturn(BUILD_ID);
        when(info.getOpenGlVersion()).thenReturn(OPEN_GL);
        when(info.getAbi()).thenReturn(ABI);
        when(info.getBootloader()).thenReturn(BOOTLOADER);
        when(info.getKernel()).thenReturn(KERNEL);
        return info;
    }
}