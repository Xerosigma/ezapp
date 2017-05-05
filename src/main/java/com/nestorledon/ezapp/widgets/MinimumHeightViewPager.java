package com.nestorledon.ezapp.widgets;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A ViewPager who's height remains the that of it's smallest child view.
 *
 * Created by nestorledon on 4/20/15.
 */
public class MinimumHeightViewPager extends ViewPager {

    private List<Integer> mList = new ArrayList<>();

    public MinimumHeightViewPager(Context context) {
        super(context);
    }

    public MinimumHeightViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    /**
     * Sets ViewPager height to the height
     * of the smallest child view.
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        mList.clear();

        int height = 0;

        for (int ii = 0; ii < getChildCount(); ii++) {
            View child = getChildAt(ii);
            child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
            mList.add(child.getMeasuredHeight());
        }

        if(!mList.isEmpty()) {
            Collections.sort(mList);
            height = mList.get(0);
        }

        heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
