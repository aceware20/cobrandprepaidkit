package com.aceware.cobrandprepaidkit;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.aceware.cobrandprepaidkit.api.ApiManager;
import com.aceware.cobrandprepaidkit.api.FirstManager;
import com.aceware.cobrandprepaidkit.model.ApiListRes;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CobrandPrepaidSdkkit {
    private Context context;
    private ApiListRes savedRes;
    private String api;
    private ArrayList<String>map=new ArrayList<>();
    private ResponseListener responseListener;

    public void setResponseCall(ResponseListener responseListener) {
        this.responseListener = responseListener;
    }

    public CobrandPrepaidSdkkit(Context context) {
        this.context = context;
//        savedRes=PreferenceManager.getInstance().getApiList(context);
        getApiList();
    }


    public void getApiList()
    {
        JSONObject object=new JSONObject();
        Call<ApiListRes>call= FirstManager.getService().getApiList("c17e7d27-4769-41a0-b675-f97d0f208863");
        call.enqueue(new Callback<ApiListRes>() {
            @Override
            public void onResponse(Call<ApiListRes> call, Response<ApiListRes> response) {
                if(response.isSuccessful() && response.body()!=null)
                {
                  ApiListRes res=response.body();
                  PreferenceManager.getInstance().setApiList(context,res);
                  PreferenceManager.getInstance().setBaseUrl(context,res.getBase_url());
                  savedRes=PreferenceManager.getInstance().getApiList(context);
                  if(savedRes.getData()!=null)
                  {

                  }
                  else
                  {

                  }

                }
            }

            @Override
            public void onFailure(Call<ApiListRes> call, Throwable t) {

            }
        });
    }
    public void initApi(String method,ArrayList<String>input)
    {
        map.clear();
        for (int i=0;i<savedRes.getData().size();i++)
        {
            if(method.equalsIgnoreCase(savedRes.getData().get(i).getMethod()))
            {
                api=savedRes.getData().get(i).getApi();
                map.addAll(savedRes.getData().get(i).getParams());
            }
        }
        if(map.size()==input.size()) {
            call(api, map, input);
        }
        else
        {
            Toast.makeText(context,"invalid input", Toast.LENGTH_SHORT).show();
        }
    }
    public void call(String api, ArrayList<String> map, ArrayList<String> input)
    {
        HashMap<String,String>map1=new HashMap<>();
        for (int i=0;i<map.size();i++)
        {
            map1.put(map.get(i),input.get(i));
        }
        try {
            Call<JSONObject>call=ApiManager.getService(context).postapi(api,map1);
            call.enqueue(new Callback<JSONObject>() {
                @Override
                public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
                    if(response.isSuccessful()&& response.body()!=null)
                    {
                        responseListener.onSuccess(response.body());
                    }
                    else
                    {
                        try {

                            JSONObject jObjError = new JSONObject(response.errorBody().string());
                            responseListener.onFailure(jObjError);
                        } catch (JSONException | IOException e) {
                            e.printStackTrace();
                        }
//                        Toast.makeText(context, "error", Toast.LENGTH_SHORT).show();

                    }
                }

                @Override
                public void onFailure(Call<JSONObject> call, Throwable t) {
//                    responseListener.onFailure(response.body());
                }
            });
        }
        catch (Exception ae)
        {
            Log.d("error",ae.toString());
        }

    }
    public interface ResponseListener
    {
        void onSuccess(JSONObject object);
        void onFailure(JSONObject object);
    }


}
