package com.nestorledon.ezapp.widgets.slidingtab;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;


/**
 * FIXME:
 * If SlidingTabLayout is aligned to bottom, it will
 * sit on top existing views!!!
 *
 * Use this SlidingTabViewPager to align SlidingTabLayout to bottom and have
 * pager content container adjust it's height.
 *
 * See an example implementation below.
 *
 * <RelativeLayout
 *   xmlns:android="http://schemas.android.com/apk/res/android"
 *   android:orientation="vertical"
 *   android:layout_width="match_parent"
 *   android:layout_height="match_parent">
 *
 *   <com.nestorledon.ezapp.widgets.slidingtab.SlidingTabViewPager
 *       android:id="@+id/homePager"
 *       android:layout_width="match_parent"
 *       android:layout_height="wrap_content">
 *
 *     <com.nestorledon.ezapp.widgets.slidingtab.SlidingTabLayout
 *         android:id="@+id/tabmenu"
 *         android:layout_width="match_parent"
 *         android:layout_height="wrap_content"/>
 *
 *   </com.nestorledon.ezapp.widgets.slidingtab.SlidingTabLayout>
 *
 * </RelativeLayout>
 *
 * Created by nestorledon on 4/20/15.
 */
public class SlidingTabViewPager extends ViewPager {

    protected boolean mHeightSet = false;
    protected int mHeight;

    public SlidingTabViewPager(Context context) {
        super(context);
    }

    // TODO: Handle top alignments? Use attributes.
    public SlidingTabViewPager(Context context, AttributeSet attrs) {
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

        // Interate through parent children and locate SlidingTabLayout
        SlidingTabLayout slidingTabLayout = null;
        for (int ii = 0; ii < getChildCount(); ii++) {
            View child = getChildAt(ii);

            if(child instanceof SlidingTabLayout) {
                child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
                slidingTabLayout = (SlidingTabLayout)child;
                break;
            }
        }

        // Adjust parent height, move slidingTabLayout to parent.
        if(slidingTabLayout != null) {

            final ViewGroup parent = ((ViewGroup)getParent());
            final int slidingHeight = slidingTabLayout.getMeasuredHeight();
            final int parentHeight = ((ViewGroup)getParent()).getMeasuredHeight();

            if(!mHeightSet && parentHeight != 0 && slidingHeight != 0) {
                mHeight = MeasureSpec.makeMeasureSpec(parentHeight - slidingHeight, MeasureSpec.EXACTLY);

                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.MATCH_PARENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);
                params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);

                removeView(slidingTabLayout);
                parent.addView(slidingTabLayout, params);

                mHeightSet = true;
            }
        }

        super.onMeasure(widthMeasureSpec, mHeight);
    }
}

