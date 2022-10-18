package com.aceware.cobrandprepaidkit;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.aceware.cobrandprepaidkit.databinding.ActivityMainBinding;

import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private Activity activity=this;
    CobrandPrepaidSdkkit testsdk;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        testsdk=new CobrandPrepaidSdkkit(activity);
        binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    ArrayList<String>map=new ArrayList<>();
                    map.add("ashindaschandroth97@gmail.com");
                    map.add("123456");
                    Log.d("inputsize",String.valueOf(map.size()));
                    testsdk.initApi("register",map);

            }
        });
        testsdk.setResponseCall(new CobrandPrepaidSdkkit.ResponseListener() {
            @Override
            public void onSuccess(JSONObject object) {
                Toast.makeText(activity,object.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(JSONObject object) {
                Toast.makeText(activity,object.toString(), Toast.LENGTH_SHORT).show();
            }
        });


    }



}