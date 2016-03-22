package com.dision.android.rtlviewpagerexample.rest.service;

import com.dision.android.rtlviewpagerexample.rest.model.Feed;

import retrofit.Callback;
import retrofit.http.GET;

public interface AppService {

    @GET("/rss")
    void getRssFeed(Callback<Feed> callback);
}
