package com.nestorledon.ezapp.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.nestorledon.ezapp.base.widgets.EZNavigable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nestorledon on 2/23/15.
 */
public class PagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragments = null;

    public PagerAdapter(FragmentManager fm) {
        super(fm);
        mFragments = new ArrayList<>();
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
}
