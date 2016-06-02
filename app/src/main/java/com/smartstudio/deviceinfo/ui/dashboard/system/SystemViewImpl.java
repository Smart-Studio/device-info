package com.smartstudio.deviceinfo.ui.dashboard.system;


import com.smartstudio.deviceinfo.R;
import com.smartstudio.deviceinfo.model.SystemInfo;
import com.smartstudio.deviceinfo.ui.BaseViewImpl;
import com.smartstudio.deviceinfo.ui.PropertyLayout;

import javax.inject.Inject;

import butterknife.BindView;

public class SystemViewImpl extends BaseViewImpl implements SystemView {

    @BindView(R.id.view_os)
    PropertyLayout mViewOs;

    @BindView(R.id.view_os_codename)
    PropertyLayout mViewOsCodename;

    @BindView(R.id.view_os_api)
    PropertyLayout mViewOsApi;

    @BindView(R.id.view_java_vm)
    PropertyLayout mViewJavaVm;

    @BindView(R.id.view_abi)
    PropertyLayout mViewAbi;

    @BindView(R.id.view_bootloader)
    PropertyLayout mViewBootloader;

    @BindView(R.id.view_opengl)
    PropertyLayout mViewOpenGL;

    @BindView(R.id.view_build_id)
    PropertyLayout mViewBuildId;

    @BindView(R.id.view_kernel)
    PropertyLayout mViewKernel;

    @Inject
    public SystemViewImpl() {

    }

    @Override
    public int getLayoutResourceId() {
        return R.layout.fragment_system;
    }

    @Override
    public void showSystemInfo(SystemInfo info) {
        mViewOs.setValue(info.getAndroidVersion());
        mViewOsCodename.setValue(info.getAndroidCodename());
        mViewOsApi.setValue(String.valueOf(info.getAndroidApi()));
        mViewJavaVm.setValue(info.getJavaVm());
        mViewAbi.setValue(info.getAbi());
        mViewBootloader.setValue(info.getBootloader());
        mViewOpenGL.setValue(info.getOpenGlVersion());
        mViewBuildId.setValue(info.getBuildId());
        mViewKernel.setValue(info.getKernel());
    }
}
