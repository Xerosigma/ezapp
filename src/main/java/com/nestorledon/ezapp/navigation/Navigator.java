package com.nestorledon.ezapp.navigation;

import android.view.View;

/**
 * Contract detailing an object that can
 * handle navigation and provide navigation information.
 * Created by nestorledon on 2/21/15.
 */
public interface Navigator {


    /**
     * Handles navigation to provided section.
     * @param adapterItem The adapter item at the current position.
     * @param view The view that triggered the navigation.
     */
    void navigate(Object adapterItem, View view);


    /**
     * Returns the key of the currently selected item.
     * @return
     */
    String getSelectedItem();
}
