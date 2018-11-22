package com.example.thomas.gymclubapp.Models;

import java.util.Map;

public class Login {
    String email;
    String password;
    Map<String, String> data;


    public Login(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Map<String, String> SetData() {
        this.data.put("email", this.email);
        this.data.put("password", this.password);
        this.data.put("rememberMe", "false");
        return this.data;
    }
}
