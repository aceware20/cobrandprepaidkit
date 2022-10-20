package com.aceware.cobrandprepaidkit.api;





import com.aceware.cobrandprepaidkit.model.ApiListRes;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface ApiService {
    @FormUrlEncoded
    @POST()
    Call<JsonObject> postapi(@Url String api, @FieldMap HashMap<String,String>map);


    @FormUrlEncoded
    @POST()
    Call<ApiListRes>getApiList(@Url String api,@FieldMap HashMap<String,String>map);

}
