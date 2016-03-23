package com.smartstudio.deviceinfo.controllers.about.atrributions;

import com.smartstudio.deviceinfo.injection.components.AttributionsComponent;
import com.smartstudio.deviceinfo.injection.scopes.PerActivity;

import dagger.Component;

@PerActivity
@Component(modules = AttributionsActivityTestModule.class)
public interface AttributionsActivityTestComponent extends AttributionsComponent {
    void inject(AttributionsActivityUnitTest test);
}
