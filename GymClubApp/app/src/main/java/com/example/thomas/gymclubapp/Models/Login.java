package com.example.thomas.gymclubapp.Models;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Map;

public class Login {
    String email;
    String password;
    JSONObject data;


    public Login(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public byte[] SetData() throws JSONException, UnsupportedEncodingException {
        this.data = new JSONObject();
        this.data.put("email", this.email);
        this.data.put("password", this.password);
        this.data.put("rememberMe", "false");
        return this.data.toString().getBytes("utf-8");
    }
}
