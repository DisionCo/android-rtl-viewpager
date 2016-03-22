package com.dision.android.rtlviewpagerexample;

import android.app.Application;

import com.dision.android.rtlviewpagerexample.rest.RestClient;

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
