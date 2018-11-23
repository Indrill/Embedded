package com.example.thomas.gymclubapp.API.GymApp;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.example.thomas.gymclubapp.API.api;
import com.example.thomas.gymclubapp.Models.Login;
import com.example.thomas.gymclubapp.Models.Register;
import com.example.thomas.gymclubapp.R;

import org.json.JSONException;

import java.io.UnsupportedEncodingException;
import java.util.Map;

public class GymAPI extends api {
    private String url;
    Map<String, String> param;

    public GymAPI(Context context) {
        super(context);
        this.url = context.getString(R.string.url);
    }

    public void Login(Response.Listener<String> onSucces, Response.ErrorListener onError, Login data) {
        String path = "/api/account/login";
        this.param.put("Content-Type", "application/json");
        call(onSucces, onError, this.url + path, null, param, data.SetData(), Request.Method.POST);
    }

    public void Register(Response.Listener<String> onSucces, Response.ErrorListener onError, Register data) throws UnsupportedEncodingException, JSONException {
        String path = "/api/account/register";
        this.param.put("Content-Type", "application/json");
        call(onSucces, onError, this.url + path, null, param, data.SetData(), Request.Method.POST);
    }
}