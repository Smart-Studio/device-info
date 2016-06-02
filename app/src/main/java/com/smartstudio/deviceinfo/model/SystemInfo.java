package com.smartstudio.deviceinfo.model;

public class SystemInfo {
    private String androidVersion;
    private String androidCodename;
    private int androidApi;
    private String javaVm;
    private String abi;
    private String bootloader;
    private String openGlVersion;
    private String buildId;
    private String kernel;

    public SystemInfo() {
    }

    public String getAndroidVersion() {
        return androidVersion;
    }

    public void setAndroidVersion(String androidVersion) {
        this.androidVersion = androidVersion;
    }

    public String getAndroidCodename() {
        return androidCodename;
    }

    public void setAndroidCodename(String androidCodename) {
        this.androidCodename = androidCodename;
    }

    public int getAndroidApi() {
        return androidApi;
    }

    public void setAndroidApi(int androidApi) {
        this.androidApi = androidApi;
    }

    public String getJavaVm() {
        return javaVm;
    }

    public void setJavaVm(String javaVm) {
        this.javaVm = javaVm;
    }

    public String getAbi() {
        return abi;
    }

    public void setAbi(String abi) {
        this.abi = abi;
    }

    public String getBootloader() {
        return bootloader;
    }

    public void setBootloader(String bootloader) {
        this.bootloader = bootloader;
    }

    public String getOpenGlVersion() {
        return openGlVersion;
    }

    public void setOpenGlVersion(String openGlVersion) {
        this.openGlVersion = openGlVersion;
    }

    public String getBuildId() {
        return buildId;
    }

    public void setBuildId(String buildId) {
        this.buildId = buildId;
    }

    public String getKernel() {
        return kernel;
    }

    public void setKernel(String kernel) {
        this.kernel = kernel;
    }
}
