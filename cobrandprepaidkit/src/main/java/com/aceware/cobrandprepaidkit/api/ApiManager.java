package com.aceware.cobrandprepaidkit.api;


import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.aceware.cobrandprepaidkit.PreferenceManager;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * The class that manages api calling using retrofit
 */
public class ApiManager {
    public static ApiService getService(Context context){

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .addNetworkInterceptor(interceptor)
                .connectTimeout(60, TimeUnit.SECONDS)
                .build();



        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(PreferenceManager.getInstance().getBaseUrl(context))
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return  retrofit.create(ApiService.class);
    }
}
