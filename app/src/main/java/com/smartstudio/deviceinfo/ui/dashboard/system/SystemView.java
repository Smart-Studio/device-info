package com.smartstudio.deviceinfo.ui.dashboard.system;

import com.smartstudio.deviceinfo.model.SystemInfo;
import com.smartstudio.deviceinfo.ui.BaseView;

public interface SystemView extends BaseView {
    void showSystemInfo(SystemInfo info);
}
