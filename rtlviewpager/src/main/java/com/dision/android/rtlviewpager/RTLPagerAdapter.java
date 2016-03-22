package com.dision.android.rtlviewpager;


import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;

/**
 * Created by Borislav on 21.3.2016 г..
 */
public class RTLPagerAdapter extends FragmentPagerAdapter{

    // variables
    private Tab[] mTabs;
    private boolean mIsRtlOrientated;

    // constructor
    public RTLPagerAdapter(FragmentManager fm, Tab[] tabs, boolean isRtlOriented) {
        super(fm);

        mTabs = tabs;
        mIsRtlOrientated = isRtlOriented;
    }

    // methods
    @Override
    public Fragment getItem(int position) {
        if (mIsRtlOrientated && mTabs != null && mTabs.length > 0) {
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
        if (mIsRtlOrientated && mTabs != null && mTabs.length > 0) {
            return mTabs[mTabs.length - position - 1].getTitle();
        } else {
            return mTabs[position].getTitle();
        }
    }

}
