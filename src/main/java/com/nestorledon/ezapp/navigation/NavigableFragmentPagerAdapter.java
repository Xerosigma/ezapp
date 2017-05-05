package com.nestorledon.ezapp.navigation;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.nestorledon.ezapp.navigation.NavigableView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nestorledon on 2/23/15.
 */
public class NavigableFragmentPagerAdapter extends FragmentPagerAdapter {

    private List<NavigableView> mFragments = new ArrayList<>();


    public NavigableFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }


    public NavigableFragmentPagerAdapter(FragmentManager fm, ArrayList fragments) {
        super(fm);

        if(!fragments.isEmpty() && fragments.get(0) instanceof NavigableView) {
            mFragments = fragments;
        } else {
            Log.e("EZApp", "Please pass a list of NavigableView's!");
        }
    }


    @Override
    public Fragment getItem(int position) {
        if(mFragments.get(position) instanceof Fragment) {
            return (Fragment) mFragments.get(position);
        } else {
            return null;
        }
    }


    @Override
    public int getCount() {
        return mFragments.size();
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return mFragments.get(position).getTitle();
    }


    public void addFragment(NavigableView navigableView) {
        mFragments.add(navigableView);
        notifyDataSetChanged();
    }
}
