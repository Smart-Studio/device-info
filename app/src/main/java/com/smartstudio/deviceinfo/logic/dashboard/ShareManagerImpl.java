package com.smartstudio.deviceinfo.logic.dashboard;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.StringRes;

public abstract class ShareManagerImpl<SHARE_TYPE> implements ShareManager<SHARE_TYPE> {
    public static final String CONTENT_TYPE = "text/plain";
    public static final String NEW_LINE = "\n";
    public static final String TAB = "\t\t";

    private final Intent mIntent;
    protected final Context mContext;
    private String mText = "";
    protected SHARE_TYPE mSharedObject;

    public ShareManagerImpl(Intent intent, Context context) {
        mIntent = intent;
        mIntent.setAction(Intent.ACTION_SEND);
        mContext = context;
    }

    @Override
    public void share(SHARE_TYPE share) {
        mText = "";
        mSharedObject = share;
        buildText();
        launchShare(getTitle(), getSubject(), mText);
        mSharedObject = null;
    }

    void launchShare(String title, String subject, String text) {
        mIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        mIntent.putExtra(Intent.EXTRA_TEXT, text);
        mIntent.setType(CONTENT_TYPE);
        mContext.startActivity(Intent.createChooser(mIntent, title));
    }

    protected abstract String getSubject();

    protected abstract void buildText();

    protected abstract String getTitle();

    protected void addTitle(String title) {
        mText += NEW_LINE + title + NEW_LINE + NEW_LINE;
    }

    protected void addProperty(String name, String value) {
        mText += name + TAB + value + NEW_LINE;
    }

    protected String getString(@StringRes int stringResId) {
        return mContext.getString(stringResId);
    }
}
