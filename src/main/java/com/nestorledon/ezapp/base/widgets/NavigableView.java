package com.nestorledon.ezapp.base.widgets;


import android.content.Context;
import android.view.View;


/**
 * Represents a view that provides information relevant to navigation.
 *
 * Created by nestorledon on 8/24/15.
 */
public interface NavigableView {


    /**
     * Returns the id of this views layout.
     * @return
     */
    int getLayoutId();


    /**
     * Triggers the inflation of the view.
     * @param context
     * @return
     */
    View inflate(Context context);


    /**
     * Returns the title of this view.
     * @return
     */
    String getTitle();


    /**
     * Returns the id of the icon associated with this view.
     * @return
     */
    int getIconId();


    /**
     * Should be triggered when view becomes active.
     */
    void onActive();


    /**
     * Should be triggered when view is no longer active.
     */
    void onInactive();
}
