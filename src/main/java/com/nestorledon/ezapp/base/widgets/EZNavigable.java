package com.nestorledon.ezapp.base.widgets;


/**
 * Created by nestorledon on 8/24/15.
 */
public interface EZNavigable {

    CharSequence getPageTitle();

    int getPageIconId();

    void onPageSelected();
}
