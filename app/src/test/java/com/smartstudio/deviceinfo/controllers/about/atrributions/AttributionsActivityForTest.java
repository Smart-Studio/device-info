package com.smartstudio.deviceinfo.controllers.about.atrributions;

import com.smartstudio.deviceinfo.controllers.about.attributions.AttributionsActivity;

public class AttributionsActivityForTest extends AttributionsActivity {
    AttributionsActivityTestComponent mComponent;


    @Override
    protected void initComponent() {
        mComponent = DaggerAttributionsActivityTestComponent.builder()
                .attributionsActivityTestModule(new AttributionsActivityTestModule())
                .build();
        mComponent.inject(this);
    }
}
