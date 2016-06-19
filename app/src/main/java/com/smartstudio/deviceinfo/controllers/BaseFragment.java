package com.smartstudio.deviceinfo.controllers;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(mView.getLayoutResourceId(), container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mView.init(view);
    }
}