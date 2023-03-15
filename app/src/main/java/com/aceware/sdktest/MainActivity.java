package com.aceware.sdktest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;

import com.aceware.cobrandprepaidkit.CobrandPrepaidSdkkit;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private Activity activity=this;
    private CobrandPrepaidSdkkit sdkkit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sdkkit=new CobrandPrepaidSdkkit(activity);
        ArrayList<String>map=new ArrayList<>();
        map.add("testaccount@dummy.com");
        map.add("2518");
        map.add("101100010020445");
        sdkkit.initService("ATA001344","defaultAccount",map);
        sdkkit.setResponseCall(new CobrandPrepaidSdkkit.ResponseListener() {
            @Override
            public void onSuccess(String s, JsonObject jsonObject) {

            }

            @Override
            public void onFailure(String s, JsonObject jsonObject) {

            }
        });

    }
}