package com.dision.android.rtlviewpagerexample.activities;

import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.dision.android.rtlviewpager.RTLPagerAdapter;
import com.dision.android.rtlviewpager.RTLViewPager;
import com.dision.android.rtlviewpagerexample.DisionApp;
import com.dision.android.rtlviewpagerexample.R;
import com.dision.android.rtlviewpagerexample.fragments.NewsFragment;
import com.dision.android.rtlviewpager.Tab;
import com.dision.android.rtlviewpagerexample.rest.model.Feed;
import com.rey.material.widget.ProgressView;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity{

    // constants
    public static final String BASIC_TAG = MainActivity.class.getName();

    private static final int TAB_NEWS = 1;
    private static final int TAB_PHOTOS = 2;
    private static final int TAB_VIDEOS = 3;

    private static final int SHOW_PROGRESS = 1;
    private static final int SHOW_VIEWPAGER = 2;
    private static final int SHOW_EMPTY = 3;

    // variables
    private RTLPagerAdapter mTabsAdapter;
    private Tab[] mTabs;
    private Feed mFeed;

    // UI variables
    @Bind(R.id.toolbar_activity_main)
    Toolbar toolbar;
    @Bind(R.id.tl_activity_main)
    TabLayout tl;
    @Bind(R.id.vp_activity_main)
    RTLViewPager vp;
    @Bind(R.id.pv_activity_main)
    ProgressView pv;
    @Bind(R.id.tv_activity_main_empty)
    TextView tvEmpty;

    // methods
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setToolbarUiSettings();
        getRssFeed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                onBackPressed();

                return true;
            }
            default: {
                return super.onOptionsItemSelected(item);
            }
        }
    }

    private void initTabs() {
        mTabs = new Tab[] {
                new Tab(TAB_NEWS, getString(R.string.tab_news)) {

                    @Override
                    public Fragment getFragment() {
                        return NewsFragment.getInstance(TAB_NEWS, mFeed);
                    }
                },
                new Tab(TAB_PHOTOS, getString(R.string.tab_photos)) {

                    @Override
                    public Fragment getFragment() {
                        return NewsFragment.getInstance(TAB_PHOTOS, mFeed);
                    }

                },
                new Tab(TAB_VIDEOS, getString(R.string.tab_videos)) {

                    @Override
                    public Fragment getFragment() {
                        return NewsFragment.getInstance(TAB_VIDEOS, mFeed);
                    }

                }
        };
    }

    private void setToolbarUiSettings() {
        setSupportActionBar(toolbar);
    }

    private void setAdapters() {
        // initialize adapter
        mTabsAdapter = new RTLPagerAdapter(getFragmentManager(), mTabs, true);
        // set adapter to ViewPager
        vp.setAdapter(mTabsAdapter);
        vp.isRtlOriented(true);
        tl.setupWithViewPager(vp);
    }

    private void getRssFeed() {
        showView(SHOW_PROGRESS);
        DisionApp.getRestClient().getAppService().getRssFeed(new Callback<Feed>() {
            @Override
            public void success(Feed apiResponse, Response response) {
                Log.d("Rss feed success", "News count " + apiResponse.getChannel().getFeedItems().size());

                showView(SHOW_VIEWPAGER);

                mFeed = apiResponse;

                initTabs();
                setAdapters();
            }

            @Override
            public void failure(RetrofitError error) {
                error.printStackTrace();
                showView(SHOW_EMPTY);
            }
        });
    }

    private void showView(int viewId) {
        vp.setVisibility(viewId == SHOW_VIEWPAGER ? View.VISIBLE : View.GONE);
        pv.setVisibility(viewId == SHOW_PROGRESS ? View.VISIBLE : View.GONE);
        tvEmpty.setVisibility(viewId == SHOW_EMPTY ? View.VISIBLE : View.GONE);
    }
}
