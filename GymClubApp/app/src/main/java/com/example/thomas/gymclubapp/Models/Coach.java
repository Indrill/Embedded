package com.example.thomas.gymclubapp.Models;

public class Coach {
    private String Name;
    private String Description;
    private String PhoneNbr;

    public Coach(String name, String desc, String phone) {
        this.Name = name;
        this.Description = desc;
        this.PhoneNbr = phone;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getName() {
        return this.Name;
    }

    public void setDescription(String desc) {
        this.Description = desc;
    }

    public String getDescription() {
        return this.Description;
    }

    public void setPhoneNbr(String phone) {
        this.PhoneNbr = phone;
    }

    public String getPhoneNbr() {
        return this.PhoneNbr;
    }
}
