package com.smartstudio.deviceinfo.ui.about.attributions;

import android.view.View;
import android.widget.TextView;

import com.smartstudio.deviceinfo.model.Attribution;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import butterknife.ButterKnife;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ButterKnife.class)
public class AttributionViewHolderImplTest {
    private static final String LIBRARY = "Android God";
    private static final String DESCRIPTION = "Best library ever";
    private static final String AUTHOR = "Google";
    private static final String LICENSE = "Apache 2.0";

    @Mock
    private View view;
    @Mock
    private Attribution mAttribution;

    private AttributionViewHolderImpl mHolder;

    @Before
    public void setUp() throws Exception {
        mockStatic(ButterKnife.class);
        mHolder = new AttributionViewHolderImpl(view);
        mHolder.txtLibrary = mockTextView();
        mHolder.txtDescription = mockTextView();
        mHolder.txtAuthor = mockTextView();
        mHolder.txtLicense = mockTextView();
    }

    @Test
    public void testOnBind() throws Exception {
        when(mAttribution.getLibrary()).thenReturn(LIBRARY);
        when(mAttribution.getDescription()).thenReturn(DESCRIPTION);
        when(mAttribution.getAuthor()).thenReturn(AUTHOR);
        when(mAttribution.getLicense()).thenReturn(LICENSE);

        mHolder.onBind(mAttribution);

        verify(mAttribution).getLibrary();
        verify(mAttribution).getDescription();
        verify(mAttribution).getAuthor();
        verify(mAttribution).getLicense();

        verify(mHolder.txtLibrary).setText(LIBRARY);
        verify(mHolder.txtDescription).setText(DESCRIPTION);
        verify(mHolder.txtAuthor).setText(AUTHOR);
        verify(mHolder.txtLicense).setText(LICENSE);
    }

    private TextView mockTextView() {
        return mock(TextView.class);
    }
}