package com.smartstudio.deviceinfo.injection.components;

import com.smartstudio.deviceinfo.controllers.dashboard.system.SystemFragment;
import com.smartstudio.deviceinfo.injection.modules.SystemModule;
import com.smartstudio.deviceinfo.injection.scopes.PerFragment;

import dagger.Subcomponent;

@PerFragment
@Subcomponent(modules = SystemModule.class)
public interface SystemComponent {
    void inject(SystemFragment fragment);
}
