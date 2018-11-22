package com.example.thomas.gymclubapp.Models;

import java.util.Map;

public class Register {
    String email;
    String password;
    String firstname;
    String lastname;
    Map<String, String> data;

    public Register(String email, String password, String firstname, String lastname) {
        this.email = email;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public Map<String, String> SetData() {
        this.data.put("email", this.email);
        this.data.put("password", this.password);
        this.data.put("firstname", this.firstname);
        this.data.put("lastname", this.lastname);
        this.data.put("address", "");
        this.data.put("birthday", "");
        this.data.put("gender", "");
        return this.data;
    }
}
