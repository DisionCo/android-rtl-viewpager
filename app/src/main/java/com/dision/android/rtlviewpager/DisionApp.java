package com.dision.android.rtlviewpager;

import android.app.Application;

import com.dision.android.rtlviewpager.rest.RestClient;

public class DisionApp extends Application {

    private static RestClient restClient;

    @Override
    public void onCreate()
    {
        super.onCreate();

        restClient = new RestClient();
    }

    public static RestClient getRestClient()
    {
        return restClient;
    }
}
