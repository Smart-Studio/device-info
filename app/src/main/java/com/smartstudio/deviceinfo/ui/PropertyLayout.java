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

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.smartstudio.deviceinfo.R;

/**
 * TODO Add a class header comment
 */
public class PropertyLayout extends ViewGroup {
    private TextView mTxtTitle;
    private TextView mTxtValue;

    public PropertyLayout(Context context) {
        super(context);
        init(null);
    }

    public PropertyLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public PropertyLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        inflate(getContext(), R.layout.view_property, this);
        mTxtTitle = (TextView) getChildAt(0);
        mTxtValue = (TextView) getChildAt(1);

        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.PropertyView, 0, 0);
            setTitle(a.getText(R.styleable.PropertyView_title_text));
            setValue(a.getText(R.styleable.PropertyView_value_text));
            mTxtTitle.setTextColor(a.getColor(R.styleable.PropertyView_title_color, Color.WHITE));
            mTxtTitle.setTextColor(a.getColor(R.styleable.PropertyView_value_color, Color.WHITE));
            a.recycle();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
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
        MarginLayoutParams titleParams = (MarginLayoutParams) mTxtTitle.getLayoutParams();
        MarginLayoutParams valueParams = (MarginLayoutParams) mTxtValue.getLayoutParams();

        int childLeft;
        int childRight;
        int childTop;
        int childBottom;

        //TxtTitle layout
        childLeft = titleParams.leftMargin + getPaddingLeft();
        childRight = childLeft + mTxtTitle.getMeasuredWidth();

        if (mTxtTitle.getMeasuredHeight() < mTxtValue.getMeasuredHeight()) {
            childTop = titleParams.topMargin + getPaddingTop() + mTxtValue.getMeasuredHeight() / 2
                    - mTxtTitle.getMeasuredHeight() / 2;
        } else {
            childTop = titleParams.topMargin + getPaddingTop();
        }

        childBottom = childTop + mTxtTitle.getMeasuredHeight();
        mTxtTitle.layout(childLeft, childTop, childRight, childBottom);

        //TxtValue layout
        childLeft = childRight + valueParams.leftMargin;
        childRight = childLeft + mTxtValue.getMeasuredWidth();

        if (mTxtValue.getMeasuredHeight() < mTxtTitle.getMeasuredHeight()) {
            childTop = titleParams.topMargin + getPaddingTop() + mTxtTitle.getMeasuredHeight() / 2
                    - mTxtValue.getMeasuredHeight() / 2;
        } else {
            childTop = titleParams.topMargin + getPaddingTop();
        }

        childBottom = childTop + mTxtValue.getMeasuredHeight();
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

        MarginLayoutParams params = (MarginLayoutParams) getLayoutParams();
        MarginLayoutParams childParams = (MarginLayoutParams) child.getLayoutParams();

        int padding = child == mTxtTitle ? getPaddingLeft() : getPaddingRight();
        int totalMargin = params.leftMargin + params.rightMargin;
        int margin = child == mTxtTitle ? params.leftMargin : params.rightMargin;

        int childWidthMeasureSpec = getChildMeasureSpec(parentWidthMeasureSpec,
                childParams.leftMargin + childParams.rightMargin + widthUsed, (parentWidth + totalMargin) / 2
                        - childParams.leftMargin - childParams.rightMargin - padding - margin);
        int childHeightMeasureSpec = getChildMeasureSpec(parentHeightMeasureSpec,
                childParams.topMargin + childParams.bottomMargin + heightUsed, childParams.height);

        child.measure(childWidthMeasureSpec, childHeightMeasureSpec);
    }
}
