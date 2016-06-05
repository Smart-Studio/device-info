package com.smartstudio.deviceinfo.logic.dashboard.system;

import android.content.Context;
import android.content.Intent;
import android.os.Build;

import com.smartstudio.deviceinfo.R;
import com.smartstudio.deviceinfo.injection.qualifiers.ForActivity;
import com.smartstudio.deviceinfo.logic.dashboard.ShareManagerImpl;
import com.smartstudio.deviceinfo.model.SystemInfo;

import javax.inject.Inject;

public class SystemInfoShareManagerImpl extends ShareManagerImpl<SystemInfo> implements SystemInfoShareManager {

    @Inject
    public SystemInfoShareManagerImpl(Intent intent, @ForActivity Context context, StringBuilder textBuilder) {
        super(intent, context, textBuilder);
    }

    @Override
    protected String getSubject() {
        return Build.DEVICE;
    }

    @Override
    protected void buildText() {
        addTitle(getString(R.string.android_title));
        addProperty(getString(R.string.os_version), mSharedObject.getAndroidVersion());
        addProperty(getString(R.string.os_codename), mSharedObject.getAndroidCodename());
        addProperty(getString(R.string.os_api), String.valueOf(mSharedObject.getAndroidApi()));
        addProperty(getString(R.string.java_vm), mSharedObject.getJavaVm());
        addProperty(getString(R.string.build_id), mSharedObject.getBuildId());
        addTitle(getString(R.string.others));
        addProperty(getString(R.string.opengl), mSharedObject.getOpenGlVersion());
        addProperty(getString(R.string.abi), mSharedObject.getAbi());
        addProperty(getString(R.string.bootloader), mSharedObject.getBootloader());
        addProperty(getString(R.string.kernel), mSharedObject.getKernel());
    }

    @Override
    protected String getTitle() {
        return getString(R.string.share_system_title);
    }
}
