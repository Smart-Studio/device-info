package com.smartstudio.deviceinfo.logic;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Build;

import com.smartstudio.deviceinfo.injection.qualifiers.ForApplication;
import com.smartstudio.deviceinfo.model.SystemInfo;

import javax.inject.Inject;

public class SystemInfoProviderImpl implements SystemInfoProvider {
    static final String JAVA_VM = "java.vm.version";
    static final String OS_NAME = "os.name";
    static final String OS_VERSION = "os.version";
    static final String VM_ART = "Art";
    static final String VM_DALVIK = "Dalvik";

    //Android versions codenames
    static final String CUPCAKE = "Cupcake";
    static final String DONUT = "Donut";
    static final String ECLAIR = "Eclair";
    static final String ECLAIR_0_1 = "Eclair 0 1";
    static final String ECLAIR_MR1 = "Eclair MR1";
    static final String FROYO = "Froyo";
    static final String GINGERBREAD = "Gingerbread";
    static final String GINGERBREAD_MR1 = "Gingerbread MR1";
    static final String HONEYCOMB = "Honeycomb";
    static final String HONEYCOMB_MR1 = "Honeycomb MR1";
    static final String HONEYCOMB_MR2 = "Honeycomb MR2";
    static final String ICE_CREAM_SANDWICH = "Ice Cream Sandwich";
    static final String ICE_CREAM_SANDWICH_MR1 = "Ice Cream Sandwich MR1";
    static final String JELLY_BEAN = "Jelly Bean";
    static final String JELLY_BEAN_MR1 = "Jelly Bean MR1";
    static final String JELLY_BEAN_MR2 = "Jelly Bean MR2";
    static final String KITKAT = "Kitkat";
    static final String LOLLIPOP = "Lollipop";
    static final String LOLLIPOP_MR1 = "Lollipop MR1";
    static final String MARSHMALLOW = "Marshmallow";
    static final String UNKNOWN = "unknown";

    private static final String ART_VERSION = "2";

    private final Context mContext;

    @Inject
    public SystemInfoProviderImpl(@ForApplication Context context) {
        mContext = context;
    }

    @Override
    public SystemInfo getSystemInfo() {
        SystemInfo info = new SystemInfo();
        info.setAndroidVersion(Build.VERSION.RELEASE);
        info.setAndroidCodename(getAndroidCodename());
        info.setAndroidApi(Build.VERSION.SDK_INT);
        info.setJavaVm(getJavaVm());
        info.setAbi(Build.CPU_ABI);
        info.setBootloader(Build.BOOTLOADER);
        info.setOpenGlVersion(getOpenGLVersion());
        info.setBuildId(Build.ID);
        info.setKernel(getKernelVersion());
        return info;
    }

    private String getAndroidCodename() {
        int api = Build.VERSION.SDK_INT;
        switch (api) {
            case Build.VERSION_CODES.CUPCAKE:
                return CUPCAKE;
            case Build.VERSION_CODES.DONUT:
                return DONUT;
            case Build.VERSION_CODES.ECLAIR:
                return ECLAIR;
            case Build.VERSION_CODES.ECLAIR_0_1:
                return ECLAIR_0_1;
            case Build.VERSION_CODES.ECLAIR_MR1:
                return ECLAIR_MR1;
            case Build.VERSION_CODES.FROYO:
                return FROYO;
            case Build.VERSION_CODES.GINGERBREAD:
                return GINGERBREAD;
            case Build.VERSION_CODES.GINGERBREAD_MR1:
                return GINGERBREAD_MR1;
            case Build.VERSION_CODES.HONEYCOMB:
                return HONEYCOMB;
            case Build.VERSION_CODES.HONEYCOMB_MR1:
                return HONEYCOMB_MR1;
            case Build.VERSION_CODES.HONEYCOMB_MR2:
                return HONEYCOMB_MR2;
            case Build.VERSION_CODES.ICE_CREAM_SANDWICH:
                return ICE_CREAM_SANDWICH;
            case Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1:
                return ICE_CREAM_SANDWICH_MR1;
            case Build.VERSION_CODES.JELLY_BEAN:
                return JELLY_BEAN;
            case Build.VERSION_CODES.JELLY_BEAN_MR1:
                return JELLY_BEAN_MR1;
            case Build.VERSION_CODES.JELLY_BEAN_MR2:
                return JELLY_BEAN_MR2;
            case Build.VERSION_CODES.KITKAT:
                return KITKAT;
            case Build.VERSION_CODES.LOLLIPOP:
                return LOLLIPOP;
            case Build.VERSION_CODES.LOLLIPOP_MR1:
                return LOLLIPOP_MR1;
            case Build.VERSION_CODES.M:
                return MARSHMALLOW;
            default:
                return UNKNOWN;
        }
    }

    private String getJavaVm() {
        String javaVm = System.getProperty(JAVA_VM);
        String vmName;
        if (javaVm.startsWith(ART_VERSION)) {
            vmName = VM_ART;
        } else {
            vmName = VM_DALVIK;
        }

        return vmName + " " + javaVm;
    }

    private String getOpenGLVersion() {
        return ((ActivityManager) mContext.getSystemService(Application.ACTIVITY_SERVICE))
                .getDeviceConfigurationInfo()
                .getGlEsVersion();
    }

    private String getKernelVersion() {
        return System.getProperty(OS_NAME) + " " + System.getProperty(OS_VERSION);
    }
}
