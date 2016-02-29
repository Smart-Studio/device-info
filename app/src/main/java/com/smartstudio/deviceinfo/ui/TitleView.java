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
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

import com.smartstudio.deviceinfo.R;

/**
 * TODO Add a class header comment
 */
public class TitleView extends TextView {
    private int mColor;
    private int mIndicatorHeight;
    private int mIndicatorMargin;

    public TitleView(Context context) {
        super(context);
        init(null);
    }

    public TitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        Resources resources = getResources();
        mColor = resources.getColor(R.color.colorPrimary);
        mIndicatorHeight = resources.getDimensionPixelSize(R.dimen.title_view_indicator);
        mIndicatorMargin = resources.getDimensionPixelSize(R.dimen.title_view_indicator_margin);

        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.TitleTextView);
            mColor = a.getColor(R.styleable.TitleTextView_indicator_color, mColor);
            mIndicatorHeight = a.getDimensionPixelSize(R.styleable.TitleTextView_indicator_height, mIndicatorHeight);
            mIndicatorMargin = a.getDimensionPixelSize(R.styleable.TitleTextView_indicator_margin, mIndicatorMargin);
            a.recycle();
        }

    }

    public TitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getMeasuredWidth(), getMeasuredHeight() + mIndicatorHeight + mIndicatorMargin);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(mColor);
        canvas.drawRect(0, getMeasuredHeight() - mIndicatorHeight, getMeasuredWidth(), getMeasuredHeight(), paint);
    }
}
