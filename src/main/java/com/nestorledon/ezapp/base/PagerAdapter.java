package com.nestorledon.ezapp.base;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nestorledon on 2/23/15.
 */
public class PagerAdapter extends FragmentPagerAdapter {

    List<Fragment> mFragments = null;

    public PagerAdapter(FragmentManager fm) {
        super(fm);
        mFragments = new ArrayList<Fragment>();
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    public void addFragment(Fragment fragment) {
        mFragments.add(fragment);
        notifyDataSetChanged();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return ((FragmentBase)mFragments.get(position)).getTitle();
    }
}