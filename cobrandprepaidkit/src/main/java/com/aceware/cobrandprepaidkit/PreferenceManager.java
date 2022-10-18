package com.aceware.cobrandprepaidkit;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.aceware.cobrandprepaidkit.model.ApiListRes;

public class PreferenceManager {
    private static PreferenceManager instance;
    private static SharedPreferences settings;

    public static PreferenceManager getInstance()
    {

        if(instance==null)
        {
            instance=new PreferenceManager();
        }
        return instance;
    }
    public void clearPrefs(Context context) {
        settings = context.getSharedPreferences(context.getPackageName(), context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.clear();
        editor.commit();
    }
    public void setApiList(Context context, ApiListRes apiListRes)
    {
        String object="";
        if(apiListRes!=null) {
            Gson gson = new Gson();
            object = gson.toJson(apiListRes);
        }
        SharedPreferences.Editor editor = context.getSharedPreferences(context.getPackageName(), context.MODE_PRIVATE).edit();
        editor.putString("apiListRes",object);
        editor.commit();
    }
    public ApiListRes getApiList(Context context)
    {
        settings = context.getSharedPreferences(context.getPackageName(), context.MODE_PRIVATE);
        ApiListRes apiListRes=null;
        SharedPreferences prefs= context.getSharedPreferences(context.getPackageName(), context.MODE_PRIVATE);
        String object=prefs.getString("apiListRes","");
        if(!object.isEmpty())
        {
            Gson gson = new Gson();
            apiListRes=gson.fromJson(object,ApiListRes.class);
        }
        return apiListRes;
    }
    public  void setAPI(Context context, String api)
    {
        SharedPreferences.Editor editor = context.getSharedPreferences(context.getPackageName(), context.MODE_PRIVATE).edit();
        editor.putString("api",api);
        editor.commit();
    }
    public String getAPI(Context context)
    {

        settings = context.getSharedPreferences(context.getPackageName(), context.MODE_PRIVATE);
        SharedPreferences prefs= context.getSharedPreferences(context.getPackageName(), context.MODE_PRIVATE);
        return prefs.getString("api","");
    }
    public  void setBaseUrl(Context context, String base_url)
    {
        SharedPreferences.Editor editor = context.getSharedPreferences(context.getPackageName(), context.MODE_PRIVATE).edit();
        editor.putString("base_url",base_url);
        editor.commit();
    }
    public String getBaseUrl(Context context)
    {

        settings = context.getSharedPreferences(context.getPackageName(), context.MODE_PRIVATE);
        SharedPreferences prefs= context.getSharedPreferences(context.getPackageName(), context.MODE_PRIVATE);
        return prefs.getString("base_url","");
    }







}
