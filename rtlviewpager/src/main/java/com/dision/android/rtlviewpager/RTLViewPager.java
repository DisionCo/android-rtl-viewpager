package com.dision.android.rtlviewpager;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

/**
 * A ViewPager that allows Right to Left pages scrolling for RTL locales
 * Created by Borislav on 21.3.2016 г..
 */
public class RTLViewPager extends ViewPager{

    // variables
    private boolean mIsRtlOriented;

    // constructors
    public RTLViewPager(Context context){
        this(context, null);
    }

    public RTLViewPager(Context context, AttributeSet attrs){
        super(context, attrs);
    }

    /**
     * To use this method call first {@link android.support.v4.view.ViewPager#setAdapter(android.support.v4.view.PagerAdapter)}
     * @param isRtlOriented is right to left oriented
     */
    public void isRtlOriented(boolean isRtlOriented){
        mIsRtlOriented = isRtlOriented;
        if(mIsRtlOriented && getAdapter() != null){
            setCurrentItem(getAdapter().getCount() - 1);
        }else{
            setCurrentItem(0);
        }
    }

}
