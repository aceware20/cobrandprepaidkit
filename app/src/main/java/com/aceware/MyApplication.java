package com.aceware;

import android.app.Application;

import com.aceware.cobrandprepaidkit.CobrandPrepaidSdkkit;

public class MyApplication extends Application {
    private CobrandPrepaidSdkkit testsdk;
    @Override
    public void onCreate() {
        super.onCreate();
        testsdk=new CobrandPrepaidSdkkit(getApplicationContext());
    }
}
