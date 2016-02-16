/*
 * Copyright 2016 Smart Studio.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.smartstudio.deviceinfo.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.smartstudio.deviceinfo.R;

/**
 * TODO Add a class header comment
 */
public class CustomView extends ViewGroup {
    private TextView mTxtTitle;
    private TextView mTxtValue;

    public CustomView(Context context) {
        super(context);
        init(null);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        inflate(getContext(), R.layout.view_property, this);
        mTxtTitle = (TextView) getChildAt(0);
        mTxtValue = (TextView) getChildAt(1);

        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.PropertyView, 0, 0);
            setTitle(a.getText(R.styleable.PropertyView_titleText));
            a.recycle();
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
        return new MarginLayoutParams(p);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    public void setTitle(CharSequence title) {
        mTxtTitle.setText(title);
    }

    public void setValue(CharSequence value) {
        mTxtValue.setText(value);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        MarginLayoutParams params = (MarginLayoutParams) mTxtTitle.getLayoutParams();
        mTxtTitle.layout(params.leftMargin, params.topMargin, r / 2 - params.rightMargin,
                params.topMargin + mTxtTitle.getMeasuredHeight());
        params = (MarginLayoutParams) mTxtValue.getLayoutParams();
        mTxtValue.layout(r / 2 + params.leftMargin, params.topMargin, r - params.rightMargin,
                params.topMargin + mTxtValue.getMeasuredHeight());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthConstraints = getPaddingLeft() + getPaddingRight();
        int heightConstraints = getPaddingBottom() + getPaddingTop();
        int parentWidth = MeasureSpec.getSize(widthMeasureSpec);

        measureChildWithMargins(mTxtTitle, widthMeasureSpec, widthConstraints, heightMeasureSpec, heightConstraints);
        widthConstraints += mTxtTitle.getMeasuredWidth();
        measureChildWithMargins(mTxtValue, widthMeasureSpec, widthConstraints, heightMeasureSpec, heightConstraints);

        int height = Math.max(mTxtTitle.getMeasuredHeight(), mTxtValue.getMeasuredHeight());
        MarginLayoutParams params = (MarginLayoutParams) mTxtTitle.getLayoutParams();
        setMeasuredDimension(parentWidth, height + params.topMargin + params.bottomMargin);
    }

    @Override
    protected void measureChildWithMargins(View child, int parentWidthMeasureSpec,
                                           int widthUsed, int parentHeightMeasureSpec, int heightUsed) {
        int parentWidth = MeasureSpec.getSize(parentWidthMeasureSpec);
        final MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();

        final int childWidthMeasureSpec = getChildMeasureSpec(parentWidthMeasureSpec,
                lp.leftMargin + lp.rightMargin + widthUsed, parentWidth / 2 - lp.leftMargin - lp.rightMargin);
        final int childHeightMeasureSpec = getChildMeasureSpec(parentHeightMeasureSpec,
                lp.topMargin + lp.bottomMargin + heightUsed, lp.height);

        child.measure(childWidthMeasureSpec, childHeightMeasureSpec);
    }
}
