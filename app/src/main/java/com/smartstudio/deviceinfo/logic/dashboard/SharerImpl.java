package com.smartstudio.deviceinfo.logic.dashboard;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.StringRes;

public abstract class SharerImpl<SHARE_TYPE> implements Sharer<SHARE_TYPE> {
    private static final String CONTENT_TYPE = "text/plain";
    private static final String NEW_LINE = "\n";
    private static final String TAB = "\t\t";

    private final Intent mIntent;
    protected final Context mContext;
    private final StringBuilder mTextBuilder;
    protected SHARE_TYPE mSharedObject;

    public SharerImpl(Intent intent, Context context, StringBuilder textBuilder) {
        mIntent = intent;
        mIntent.setAction(Intent.ACTION_SEND);
        mContext = context;
        mTextBuilder = textBuilder;
    }

    @Override
    public void share(SHARE_TYPE share) {
        mTextBuilder.setLength(0);
        mSharedObject = share;
        buildText();
        launchShare(getTitle(), getSubject(), mTextBuilder.toString());
        mSharedObject = null;
    }

    protected void launchShare(String title, String subject, String text) {
        mIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        mIntent.putExtra(Intent.EXTRA_TEXT, text);
        mIntent.setType(CONTENT_TYPE);
        mContext.startActivity(Intent.createChooser(mIntent, title));
    }

    protected abstract String getSubject();

    protected abstract void buildText();

    protected abstract String getTitle();

    protected void addTitle(String title) {
        mTextBuilder.append(NEW_LINE)
                .append(title)
                .append(NEW_LINE)
                .append(NEW_LINE);
    }

    protected void addProperty(String name, String value) {
        mTextBuilder.append(name)
                .append(TAB)
                .append(value)
                .append(NEW_LINE);
    }

    protected String getString(@StringRes int stringResId) {
        return mContext.getString(stringResId);
    }

}
