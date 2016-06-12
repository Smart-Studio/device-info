package com.smartstudio.deviceinfo.ui.dashboard.system;

import com.smartstudio.deviceinfo.model.SystemInfo;
import com.smartstudio.deviceinfo.ui.dashboard.DashboardContentView;

public interface SystemView extends DashboardContentView {
    void showSystemInfo(SystemInfo info);
}
