package com.smartstudio.deviceinfo.utils;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.smartstudio.deviceinfo.R;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.verification.VerificationMode;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.verifyStatic;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Toast.class)
public class ViewUtilsTest {
    private static final String NO_BROWSER_ERROR = "No browser!";

    @Mock
    private Context mContext;
    @Mock
    private Toast mToast;
    private Toast mPreExistingToast;
    private View mToastView;

    @Before
    public void setUp() throws Exception {
        mockStatic(Toast.class);
        when(Toast.makeText(mContext, NO_BROWSER_ERROR, Toast.LENGTH_LONG)).thenReturn(mToast);
        when(mContext.getString(R.string.error_no_browser)).thenReturn(NO_BROWSER_ERROR);
    }

    @Test
    public void testShowNoBrowserToast() throws Exception {
        Toast toast = ViewUtils.showNoBrowserToast(mContext, null);

        verifyMocks(true);
        verify(toast, never()).getView();
        assertThat(toast).isEqualTo(mToast);
    }

    @Test
    public void testPreExistingShown() throws Exception {
        mockPreExistingToast(true);

        Toast toast = ViewUtils.showNoBrowserToast(mContext, mPreExistingToast);

        verifyMocks(false);
        verifyPreExistingToast();
        assertThat(toast).isEqualTo(mPreExistingToast);
    }

    @Test
    public void testPreExistingNotShown() throws Exception {
        mockPreExistingToast(false);

        Toast toast = ViewUtils.showNoBrowserToast(mContext, mPreExistingToast);

        verifyMocks(true);
        verifyPreExistingToast();
        assertThat(toast).isEqualTo(mToast);
    }

    private void mockPreExistingToast(boolean shown) {
        mPreExistingToast = mock(Toast.class);
        mToastView = mock(View.class);
        when(mToastView.isShown()).thenReturn(shown);
        when(mPreExistingToast.getView()).thenReturn(mToastView);
    }

    private void verifyMocks(boolean toastCreated) {
        VerificationMode verificationMode = toastCreated ? times(1) : never();

        verify(mContext, verificationMode).getString(R.string.error_no_browser);
        verifyStatic(verificationMode);
        Toast.makeText(mContext, NO_BROWSER_ERROR, Toast.LENGTH_LONG);
        verify(mToast, verificationMode).show();
    }

    private void verifyPreExistingToast(){
        verify(mPreExistingToast).getView();
        verify(mToastView).isShown();
    }
}