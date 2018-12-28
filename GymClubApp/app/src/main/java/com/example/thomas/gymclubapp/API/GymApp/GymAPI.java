package com.example.thomas.gymclubapp.API.GymApp;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.example.thomas.gymclubapp.API.api;
import com.example.thomas.gymclubapp.Models.Login;
import com.example.thomas.gymclubapp.Models.Register;

import org.json.JSONException;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class GymAPI extends api {
    private String url;
    private String auth;

    public GymAPI(Context context) {
        super(context);
        this.url = "https://dc9212d0.ngrok.io";
        this.auth =  "Auth";
    }

    public void Login(Response.Listener<String> onSucces, Response.ErrorListener onError, Login data) {
        String path = "/api/account/login";
        Map<String, String> param = new HashMap<String, String>();
        param.put("Content-Type", "application/json");
        try {
            call(onSucces, onError, this.url + path, this.auth, param, data.SetData(), Request.Method.POST);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void Register(Response.Listener<String> onSucces, Response.ErrorListener onError, Register data) throws UnsupportedEncodingException, JSONException {
        String path = "/api/account/register";
        Map<String, String> param = new HashMap<String, String>();
        param.put("Content-Type", "application/json");
        call(onSucces, onError, this.url + path, this.auth, param, data.SetData(), Request.Method.POST);
    }

    public void getTrainers(Response.Listener<String> onSucces, Response.ErrorListener onError) {
        String path = "/api/trainers/listTrainers";
        Map<String, String> param = new HashMap<String, String>();
        param.put("Content-Type", "application/json");
        call(onSucces, onError, this.url + path, this.auth, param, null, Request.Method.GET);
    }
}