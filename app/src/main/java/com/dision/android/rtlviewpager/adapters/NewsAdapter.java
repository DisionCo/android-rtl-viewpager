package com.dision.android.rtlviewpager.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dision.android.rtlviewpager.R;
import com.dision.android.rtlviewpager.rest.model.FeedItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NewsAdapter
        extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {

    // variables
    private Context mContext;
    private List<FeedItem> mData;

    // constructor
    public NewsAdapter(Context context, List<FeedItem> data) {
        this.mContext = context;
        this.mData = data;
    }

    // methods
    public void addData(FeedItem[] data, boolean notify) {
        addData(Arrays.asList(data), notify);
    }

    public void addData(List<FeedItem> data, boolean notify) {
        int startCount = mData.size();

        mData.addAll(data);

        if (notify) {
            try {
                notifyItemRangeInserted(startCount, data.size());
            } catch (IndexOutOfBoundsException e) {
                notifyDataSetChanged();
                e.printStackTrace();
            }
        }
    }

    public void clearData(boolean notify) {
        mData.clear();

        if (notify) {
            notifyDataSetChanged();
        }
    }

    public List<FeedItem> getData() {
        return mData;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public FeedItem getItem(int position) {
        return mData.get(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NewsHolder(LayoutInflater.from(mContext)
                .inflate(R.layout.item_news, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        NewsHolder viewHolder = (NewsHolder) holder;
        FeedItem item = mData.get(position);

        viewHolder.tvTitle.setText(item.getTitle());
        viewHolder.tvDate.setText(item.getDescription());
    }

    // inner classes
    public class NewsHolder extends RecyclerView.ViewHolder {

        // UI variables
        @Bind(R.id.tv_item_news_title)
        TextView tvTitle;
        @Bind(R.id.tv_item_news_date)
        TextView tvDate;

        // constructor
        public NewsHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
