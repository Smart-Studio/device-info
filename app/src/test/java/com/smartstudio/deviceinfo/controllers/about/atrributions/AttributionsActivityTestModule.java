package com.smartstudio.deviceinfo.controllers.about.atrributions;

import com.smartstudio.deviceinfo.R;
import com.smartstudio.deviceinfo.analytics.about.attributions.AttributionsAnalytics;
import com.smartstudio.deviceinfo.injection.qualifiers.ForActivity;
import com.smartstudio.deviceinfo.injection.qualifiers.ForFabric;
import com.smartstudio.deviceinfo.injection.qualifiers.ForGoogle;
import com.smartstudio.deviceinfo.injection.scopes.PerActivity;
import com.smartstudio.deviceinfo.logic.attributions.AttributionsProvider;
import com.smartstudio.deviceinfo.ui.BaseView;
import com.smartstudio.deviceinfo.ui.about.attributions.AttributionsView;

import dagger.Module;
import dagger.Provides;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Module
public class AttributionsActivityTestModule {

    @Provides
    @PerActivity
    AttributionsView provideView() {
        AttributionsView view = mock(AttributionsView.class);
        when(view.getLayoutResourceId()).thenReturn(R.layout.activity_attributions);
        return view;
    }

    @Provides
    @PerActivity
    @ForActivity
    BaseView provideBaseView(AttributionsView view) {
        return view;
    }

    @Provides
    @PerActivity
    AttributionsProvider provideAttributionProvider() {
        return mock(AttributionsProvider.class);
    }

    @Provides
    @ForGoogle
    @PerActivity
    AttributionsAnalytics provideAnalytics() {
        return mock(AttributionsAnalytics.class);
    }

    @Provides
    @ForFabric
    @PerActivity
    AttributionsAnalytics provideFabricAnalytics() {
        return mock(AttributionsAnalytics.class);
    }
}
