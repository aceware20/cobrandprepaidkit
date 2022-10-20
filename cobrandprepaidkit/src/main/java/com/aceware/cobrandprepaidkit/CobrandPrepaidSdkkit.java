package com.aceware.cobrandprepaidkit;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.aceware.cobrandprepaidkit.api.ApiManager;
import com.aceware.cobrandprepaidkit.api.FirstManager;
import com.aceware.cobrandprepaidkit.model.ApiListRes;
import com.aceware.cobrandprepaidkit.model.ErrorMsg;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

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
        new Thread(()->{
            getApiList();
        }).start();

    }
//    public init(Context context) {
//        this.context = context;
////        savedRes=PreferenceManager.getInstance().getApiList(context);
//        new Thread(()->{
//            getApiList();
//        }).start();
//
//    }


    private void getApiList()
    {
        JSONObject object=new JSONObject();
        HashMap<String,String>map=new HashMap<>();
        map.put("agent_email","sruthys240@gmail.com");
        map.put("mpin","123456");
        map.put("username","ashin");
        Call<ApiListRes>call= FirstManager.getService().getApiList("api/login-sdk-mpin",map);
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
    private void call(String api, ArrayList<String> map, ArrayList<String> input)
    {
        HashMap<String,String>map1=new HashMap<>();
        for (int i=0;i<map.size();i++)
        {
            map1.put(map.get(i),input.get(i));
        }
        try {
            Call<JsonObject>call=ApiManager.getService(context).postapi(api,map1);
            call.enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                    if(response.isSuccessful()&& response.body()!=null)
                    {
                        //                            JSONObject jsonSuccess=new JSONObject(response.body()..toString());
                        Log.d("sucess-data",new Gson().toJson(response.body()));
                        responseListener.onSuccess(response.body());

                    }
//                    else
//                    {
//
//
////                        try {
////
////                            JsonObject jObjError = new JsonObject(response.body());
////                            responseListener.onFailure(jObjError);
////                        } catch (JSONException | IOException e) {
////                            e.printStackTrace();
////                        }
////                        Toast.makeText(context, "error", Toast.LENGTH_SHORT).show();
//
//                    }
                }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {
                    JsonObject object = new Gson().fromJson(t.getMessage(), JsonObject.class);
                    Log.d("fail-data",new Gson().toJson(object));
                    responseListener.onFailure(object);

//                      String msg=t.getMessage();
//                      ErrorMsg errorMsg=new ErrorMsg();
//                      errorMsg.setMessage(msg);
//                      new Gson().toJson(errorMsg);
//                      JsonObject jsonObject=new JsonObject();
//                      jsonObject.
//                    responseListener.onFailure(response.body());
//                    try {
////                        JsonObject object=new JsonObject(t.getMessage());
////                        responseListener.onFailure(object);
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }

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
        void onSuccess(JsonObject object);
        void onFailure(JsonObject object);
    }


}
