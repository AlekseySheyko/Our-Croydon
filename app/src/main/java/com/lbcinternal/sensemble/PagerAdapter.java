package com.lbcinternal.sensemble;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.lbcinternal.sensemble.MainActivity.CategoryFragment;

public class PagerAdapter extends FragmentPagerAdapter {

    final int NUM_ITEMS = 2;

    String[] mTabTitles;

    public PagerAdapter(FragmentManager fm, Context context) {
        super(fm);

        mTabTitles = new String[] {
                context.getString(R.string.title_section1).toUpperCase(),
                context.getString(R.string.title_section2).toUpperCase(),
        };
    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    @Override
    public Fragment getItem(int position) {
        return CategoryFragment.newInstance(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTabTitles[position];
    }
}
