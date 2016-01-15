package com.dision.android.rtlviewpager.rest.service;

import com.dision.android.rtlviewpager.rest.model.Feed;

import retrofit.Callback;
import retrofit.http.GET;

public interface AppService {

    @GET("/rss")
    void getRssFeed(Callback<Feed> callback);
}
