package com.dision.android.rtlviewpager.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dision.android.rtlviewpager.R;
import com.dision.android.rtlviewpager.adapters.NewsAdapter;
import com.dision.android.rtlviewpager.rest.model.Feed;
import com.dision.android.rtlviewpager.ui.DividerItemDecoration;
import com.rey.material.widget.ProgressView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NewsFragment extends Fragment {

    // constants
    public static final String BASIC_TAG = NewsFragment.class.getName();

    private static final String BUNDLE_TAB_POSITION = "tab_position";
    private static final String BUNDLE_FEED = "feed";

    private static final int SHOW_RV = 1;
    private static final int SHOW_PROGRESS_VIEW = 2;
    private static final int SHOW_EMPTY_NEWS = 3;

    // variables
    private int mTabPosition;
    private Feed mFeed;
    private NewsAdapter mAdapter;

    // UI variables
    @Bind(R.id.rv_fragment_news)
    RecyclerView rvNews;
    @Bind(R.id.pv_fragment_news)
    ProgressView pv;
    @Bind(R.id.tv_fragment_news_empty)
    TextView tvEmpty;

    // get instance methods
    public static NewsFragment getInstance(int tabPosition, Feed feed) {
        NewsFragment fragment = new NewsFragment();

        Bundle args = new Bundle();
        args.putInt(BUNDLE_TAB_POSITION, tabPosition);
        args.putParcelable(BUNDLE_FEED, feed);

        fragment.setArguments(args);

        return fragment;
    }

    // methods
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);

        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initArgs();
        initVariables();
        setUiSettings();
    }

    private void initArgs() {
        mTabPosition = getArguments().getInt(BUNDLE_TAB_POSITION);
        mFeed = getArguments().getParcelable(BUNDLE_FEED);
    }

    private void initVariables() {
        mAdapter = new NewsAdapter(getActivity(), mFeed.getChannel().getFeedItems());
    }

    private void setUiSettings() {
        rvNews.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvNews.addItemDecoration(new DividerItemDecoration(getActivity(),
                DividerItemDecoration.VERTICAL_LIST));
        rvNews.setAdapter(mAdapter);

        if (mAdapter.getItemCount() == 0) {
            showView(SHOW_EMPTY_NEWS);
        } else {
            showView(SHOW_RV);
        }
    }

    private void showView(int viewId) {
        pv.setVisibility(viewId == SHOW_PROGRESS_VIEW ? View.VISIBLE : View.GONE);
        rvNews.setVisibility(viewId == SHOW_RV ? View.VISIBLE : View.GONE);
        tvEmpty.setVisibility(viewId == SHOW_EMPTY_NEWS ? View.VISIBLE : View.GONE);
    }
}
