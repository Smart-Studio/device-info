package com.smartstudio.deviceinfo.controllers.dashboard.system;

import android.content.Context;

public class SystemFragmentForTest extends SystemFragment {
    SystemFragmentTestComponent mComponent;

    @Override
    protected void initComponent(Context context) {
        mComponent = DaggerSystemFragmentTestComponent.builder()
                .systemFragmentTestModule(new SystemFragmentTestModule())
                .build();
        mComponent.inject(this);
    }
}
