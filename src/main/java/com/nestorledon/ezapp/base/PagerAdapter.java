package com.nestorledon.ezapp.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.nestorledon.ezapp.navigation.NavigableView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nestorledon on 2/23/15.
 */
public class PagerAdapter extends FragmentPagerAdapter {

    private List<NavigableView> mFragments = new ArrayList<>();


    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }


    public PagerAdapter(FragmentManager fm, ArrayList fragments) {
        super(fm);
        mFragments = fragments;
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
