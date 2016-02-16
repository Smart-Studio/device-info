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
            setValue(a.getText(R.styleable.PropertyView_valueText));
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
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        final MarginLayoutParams titleParams = (MarginLayoutParams) mTxtTitle.getLayoutParams();
        final MarginLayoutParams valueParams = (MarginLayoutParams) mTxtValue.getLayoutParams();

        int childLeft;
        int childRight;
        int childTop;
        int childBottom;

        /*if (mTxtTitle.getMeasuredHeight() < mTxtValue.getMeasuredHeight()) {
            childLeft = titleParams.leftMargin + getPaddingLeft();
            childRight = right / 2 - titleParams.rightMargin - getPaddingRight();
            childTop = (getMeasuredHeight() - mTxtTitle.getMeasuredHeight()) / 2 + titleParams.topMargin - titleParams.bottomMargin - getPaddingTop();
            childBottom = childTop + mTxtTitle.getMeasuredHeight();
        } else {*/
        childLeft = titleParams.leftMargin + getPaddingLeft();
        childRight = childLeft + mTxtTitle.getMeasuredWidth();
        childTop = titleParams.topMargin + getPaddingTop();
        childBottom = childTop + mTxtTitle.getMeasuredHeight();
        /*}*/

        mTxtTitle.layout(childLeft, childTop, childRight, childBottom);

        childLeft = childRight + valueParams.leftMargin;
        childRight = childLeft + mTxtValue.getMeasuredWidth();
        childTop = titleParams.topMargin + getPaddingTop();
        childBottom = childBottom + mTxtValue.getMeasuredHeight();

        mTxtValue.layout(childLeft, childTop, childRight, childBottom);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthPadding = getPaddingLeft() + getPaddingRight();
        int heightPadding = getPaddingBottom() + getPaddingTop();

        int parentWidth = MeasureSpec.getSize(widthMeasureSpec);

        measureChildWithMargins(mTxtTitle, widthMeasureSpec, widthPadding, heightMeasureSpec, heightPadding);
        measureChildWithMargins(mTxtValue, widthMeasureSpec, widthPadding, heightMeasureSpec, heightPadding);

        int height = Math.max(mTxtTitle.getMeasuredHeight(), mTxtValue.getMeasuredHeight());

        MarginLayoutParams params = (MarginLayoutParams) mTxtTitle.getLayoutParams();
        //TODO Handle textViews margins

        setMeasuredDimension(parentWidth, height + params.topMargin + params.bottomMargin + heightPadding);
    }

    @Override
    protected void measureChildWithMargins(View child, int parentWidthMeasureSpec,
                                           int widthUsed, int parentHeightMeasureSpec, int heightUsed) {
        int parentWidth = MeasureSpec.getSize(parentWidthMeasureSpec);

        int padding = child == mTxtTitle ? getPaddingLeft() : getPaddingRight();

        final MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();

        final int childWidthMeasureSpec = getChildMeasureSpec(parentWidthMeasureSpec,
                lp.leftMargin + lp.rightMargin + widthUsed, parentWidth / 2 - lp.leftMargin - lp.rightMargin) - padding;
        final int childHeightMeasureSpec = getChildMeasureSpec(parentHeightMeasureSpec,
                lp.topMargin + lp.bottomMargin + heightUsed, lp.height);

        child.measure(childWidthMeasureSpec, childHeightMeasureSpec);
    }
}
