package com.dision.android.rtlviewpager.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.dision.android.rtlviewpager.models.Tab;

public class TabsAdapter extends FragmentPagerAdapter {

    // variables
    private Tab[] mTabs;
    private boolean mIsRtlOrientation;

    // constructor
    public TabsAdapter(FragmentManager fm, Tab[] tabs, boolean isRtlOrientation) {
        super(fm);

        mTabs = tabs;
        mIsRtlOrientation = isRtlOrientation;
    }

    // methods
    @Override
    public Fragment getItem(int position) {
        if (mIsRtlOrientation && mTabs != null && mTabs.length > 0) {
            return mTabs[mTabs.length - position - 1].getFragment();
        } else {
            return mTabs[position].getFragment();
        }
    }

    @Override
    public int getCount() {
        return mTabs.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (mIsRtlOrientation && mTabs != null && mTabs.length > 0) {
            return mTabs[mTabs.length - position - 1].getTitle();
        } else {
            return mTabs[position].getTitle();
        }
    }
}
