package com.nestorledon.ezapp.base;

import android.view.View;

/**
 * Contract detailing an object that can
 * handle navigation and provide navigation options.
 * Created by nestorledon on 2/21/15.
 */
public interface Navigator {

    /**
     * Returns an array of navigation options.
     * @return
     */
    String[] getSections();

    /**
     * Returns an array of navigation options
     * that should contain rules.
     * @return
     */
    String[] getSectionRules();

    /**
     *
     * Returns the id for this navigation
     * items resource.
     * @param section the navigation section.
     * @param active true to receive the active resource.
     * @return
     */
    int getIconResourceId(String section, boolean active);

    /**
     * Handles navigation to provided section.
     * @param sectionName The name of the section to navigate to.
     * @param view The view that triggered the navigation.
     */
    void navigate(String sectionName, View view);

    /**
     * Returns the key of the currently selected item.
     * @return
     */
    String getSelectedItem();
}
