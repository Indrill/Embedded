package com.example.thomas.gymclubapp.Models;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;


public class Register {
    String email;
    String password;
    String firstname;
    String lastname;
    JSONObject data;

    public Register(String email, String password, String firstname, String lastname) {
        this.email = email;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public byte[] SetData() throws JSONException, UnsupportedEncodingException {
        this.data = new JSONObject();
        this.data.put("email", this.email);
        this.data.put("password", this.password);
        this.data.put("firstname", this.firstname);
        this.data.put("lastname", this.lastname);
        this.data.put("address", "");
        this.data.put("birthday", "");
        this.data.put("gender", "");
        return this.data.toString().getBytes("utf-8");
    }
}
