package com.smartstudio.deviceinfo.logic.dashboard.screeninfo;

import android.content.Context;
import android.content.Intent;

import com.smartstudio.deviceinfo.R;
import com.smartstudio.deviceinfo.injection.qualifiers.ForActivity;
import com.smartstudio.deviceinfo.logic.dashboard.ShareManagerImpl;
import com.smartstudio.deviceinfo.model.ScreenInfoViewModel;

import javax.inject.Inject;

public class ScreenInfoShareManagerImpl extends ShareManagerImpl<ScreenInfoViewModel> implements ScreenInfoShareManager {

    @Inject
    public ScreenInfoShareManagerImpl(Intent intent, @ForActivity Context context) {
        super(intent, context);
    }

    @Override
    protected String getSubject() {
        return mSharedObject.getModel();
    }

    @Override
    protected void buildText() {
        addTitle(getString(R.string.device_info_title));
        addProperty(getString(R.string.model), mSharedObject.getModel());
        addProperty(getString(R.string.manufacturer), mSharedObject.getManufacturer());
        addTitle(getString(R.string.screen_density_title));
        addProperty(getString(R.string.screen_density), mSharedObject.getDensity());
        addProperty(getString(R.string.screen_density_code), mSharedObject.getDensityCode());
        addProperty(getString(R.string.screen_density_x), mSharedObject.getDensityX());
        addProperty(getString(R.string.screen_density_y), mSharedObject.getDensityY());
        addTitle(getString(R.string.screen_size_title));
        addProperty(getString(R.string.screen_resolution), mSharedObject.getResolution());
        addProperty(getString(R.string.screen_inches), mSharedObject.getInches());
        addProperty(getString(R.string.screen_size), mSharedObject.getScreenSize());
        addProperty(getString(R.string.status_bar_height), mSharedObject.getStatusBarHeight());
        addProperty(getString(R.string.navigation_bar_height), mSharedObject.getNavBarHeight());
        addTitle(getString(R.string.screen_content_title));
        addProperty(getString(R.string.screen_content_height), mSharedObject.getContentHeight());
        addProperty(getString(R.string.screen_action_bar_height), mSharedObject.getActionBarHeight());
        addProperty(getString(R.string.screen_content_top), mSharedObject.getContentTop());
        addProperty(getString(R.string.screen_content_bottom), mSharedObject.getContentBottom());
    }

    @Override
    protected String getTitle() {
        return mContext.getString(R.string.share_screen_info_title);
    }
}
