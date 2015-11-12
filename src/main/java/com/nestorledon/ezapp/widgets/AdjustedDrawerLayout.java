package com.nestorledon.ezapp.widgets;

import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.util.AttributeSet;


/**
 * Address some issues regarding drawer height.
 * Created by nestorledon on 2/24/15.
 */
public class AdjustedDrawerLayout extends DrawerLayout {

    private final static String TAG = AdjustedDrawerLayout.class.getCanonicalName();


    public AdjustedDrawerLayout(Context context) {
        super(context);
    }

    public AdjustedDrawerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AdjustedDrawerLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        widthMeasureSpec = MeasureSpec.makeMeasureSpec(
                MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.EXACTLY);
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(
                MeasureSpec.getSize(heightMeasureSpec), MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
