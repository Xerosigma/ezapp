package com.nestorledon.ezapp.base.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;


/**
 * Created by nestorledon on 8/26/15.
 */
public abstract class NavigableViewViewBase extends LinearLayout implements NavigableView {

    public NavigableViewViewBase(Context context) {
        super(context);
    }


    public NavigableViewViewBase(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public NavigableViewViewBase(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
