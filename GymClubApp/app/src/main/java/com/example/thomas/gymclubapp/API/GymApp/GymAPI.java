package com.example.thomas.gymclubapp.API.GymApp;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.example.thomas.gymclubapp.API.api;
import com.example.thomas.gymclubapp.Models.Login;
import com.example.thomas.gymclubapp.Models.Register;
import com.example.thomas.gymclubapp.R;

import java.util.Map;

public class GymAPI extends api {
    private String url;

    public GymAPI(Context context) {
        super(context);
        this.url = context.getString(R.string.url);
    }

    public void Login(Response.Listener<String> onSucces, Response.ErrorListener onError, Login mParams) {
        String path = "/api/account/login";
        call(onSucces, onError, this.url + path, null, mParams.SetData(), null, Request.Method.POST);
    }

    public void Register(Response.Listener<String> onSucces, Response.ErrorListener onError, Register mParams) {
        String path = "/api/account/register";
        call(onSucces, onError, this.url + path, null, mParams.SetData(), null, Request.Method.POST);
    }
}
