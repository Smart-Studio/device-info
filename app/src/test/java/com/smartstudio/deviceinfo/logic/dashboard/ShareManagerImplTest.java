package com.smartstudio.deviceinfo.logic.dashboard;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.StringRes;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.reflect.Whitebox;

import static com.smartstudio.deviceinfo.logic.dashboard.ShareManagerImpl.NEW_LINE;
import static com.smartstudio.deviceinfo.logic.dashboard.ShareManagerImpl.TAB;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.verifyStatic;
import static org.powermock.api.mockito.PowerMockito.when;

@PrepareForTest({Intent.class, StringBuilder.class})
public abstract class ShareManagerImplTest<SHARE_TYPE> {
    private static final String TITLE = "Title";
    private static final String SUBJECT = "Subject";
    private static final String TEXT = "text";
    private static final String PROPERTY_NAME = "prop_name";
    private static final String PROPERTY_VALUE = "prop_value";

    @Mock
    protected Intent mMockIntent;
    @Mock
    protected Context mMockContext;
    @Mock
    protected Intent mMockChooserIntent;

    private ShareManagerImpl<SHARE_TYPE> mShareManager;

    @Before
    public void setUp() throws Exception {
        mShareManager = buildShareManager();
        mockStatic(Intent.class);
    }


    protected abstract ShareManagerImpl<SHARE_TYPE> buildShareManager();

    @Test
    public void testShare() throws Exception {
        SHARE_TYPE sharedObject = getSharedObject();
        mShareManager.share(sharedObject);
        testBuildText();
        testGetTitle();
    }

    protected abstract SHARE_TYPE getSharedObject();

    protected abstract void testBuildText();

    protected abstract void testGetTitle();

    @Test
    public void testLaunchShare() throws Exception {
        when(Intent.createChooser(eq(mMockIntent), anyString())).thenReturn(mMockChooserIntent);

        mShareManager.launchShare(TITLE, SUBJECT, TEXT);
        verify(mMockIntent).putExtra(Intent.EXTRA_SUBJECT, SUBJECT);
        verify(mMockIntent).putExtra(Intent.EXTRA_TEXT, TEXT);
        verify(mMockIntent).setType(ShareManagerImpl.CONTENT_TYPE);
        verify(mMockContext).startActivity(mMockChooserIntent);

        verifyStatic();
        Intent.createChooser(mMockIntent, TITLE);
    }


    @Test
    public void testAddTitle() throws Exception {
        mShareManager.addTitle(TITLE);
        String text = Whitebox.getInternalState(mShareManager, "mText");
        assertThat(text).isEqualTo(ShareManagerImpl.NEW_LINE + TITLE + ShareManagerImpl.NEW_LINE
                + ShareManagerImpl.NEW_LINE);
    }

    @Test
    public void testAddProperty() throws Exception {
        mShareManager.addProperty(PROPERTY_NAME, PROPERTY_VALUE);
        String text = Whitebox.getInternalState(mShareManager, "mText");
        assertThat(text).isEqualTo(PROPERTY_NAME + ShareManagerImpl.TAB + PROPERTY_VALUE + ShareManagerImpl.NEW_LINE);
    }

    @Test
    public void testGetString() throws Exception {
        when(mMockContext.getString(anyInt())).thenReturn(TITLE);
        mShareManager.getString(anyInt());
        verify(mMockContext).getString(anyInt());
    }

    protected String addTitle(String title) {
        return NEW_LINE + title + NEW_LINE + NEW_LINE;
    }

    protected String addProperty(String name, String value) {
        return name + TAB + value + NEW_LINE;
    }

    protected void mockString(@StringRes int stringRes, String value) {
        Mockito.when(mMockContext.getString(stringRes)).thenReturn(value);
    }

    protected void verifyString(@StringRes int stringRes) {
        verify(mMockContext).getString(stringRes);
    }
}