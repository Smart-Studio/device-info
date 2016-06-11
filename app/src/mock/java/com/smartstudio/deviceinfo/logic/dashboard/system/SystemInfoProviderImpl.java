package com.smartstudio.deviceinfo.logic.dashboard.system;

import com.smartstudio.deviceinfo.model.SystemInfo;

import javax.inject.Inject;

public class SystemInfoProviderImpl implements SystemInfoProvider {
    public static final String ANDROID_VERSION = "4.0";
    public static final String ICE_CREAM_SANDWICH = "Ice Cream Sandwich";
    public static final int ANDROID_API = 15;
    public static final String JAVA_VM = "Art 2.0";
    public static final String CPU_ABI = "x86";
    public static final String BOOTLOADER = "BHZ10m";
    public static final String OPEN_GL = "2.0";
    public static final String BUILD_ID = "MRA58K";
    public static final String KERNEL = "Linux 3.10.0";

    @Inject
    public SystemInfoProviderImpl() {

    }

    @Override
    public SystemInfo getSystemInfo() {
        SystemInfo info = new SystemInfo();
        info.setAndroidVersion(ANDROID_VERSION);
        info.setAndroidCodename(ICE_CREAM_SANDWICH);
        info.setAndroidApi(ANDROID_API);
        info.setJavaVm(JAVA_VM);
        info.setAbi(CPU_ABI);
        info.setBootloader(BOOTLOADER);
        info.setOpenGlVersion(OPEN_GL);
        info.setBuildId(BUILD_ID);
        info.setKernel(KERNEL);
        return info;
    }
}
