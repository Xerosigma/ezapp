package com.nestorledon.ezapp.navigation;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;


/**
 * A sample implementation of an {@link NavigableView}
 *
 * Created by nestorledon on 8/26/15.
 */
public abstract class NavigableViewBase extends LinearLayout implements NavigableView {

    public NavigableViewBase(Context context) {
        super(context);
    }


    public NavigableViewBase(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public NavigableViewBase(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
