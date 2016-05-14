package com.smartstudio.deviceinfo.controllers;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.smartstudio.deviceinfo.injection.qualifiers.ForFragment;
import com.smartstudio.deviceinfo.ui.BaseView;

import javax.inject.Inject;

public abstract class BaseFragment extends Fragment {
    @Inject
    @ForFragment
    BaseView mView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        initComponent(context);
    }

    protected abstract void initComponent(Context context);

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mView.init(view);
    }
}