package com.smartstudio.deviceinfo.controllers.dashboard.system;

import com.smartstudio.deviceinfo.injection.components.SystemComponent;
import com.smartstudio.deviceinfo.injection.scopes.PerFragment;

import dagger.Component;

@PerFragment
@Component(modules = SystemFragmentTestModule.class)
public interface SystemFragmentTestComponent extends SystemComponent {
    void inject(SystemFragmentUnitTest test);
}
