package com.example.final_project;

import androidx.annotation.NonNull;

public class UserData {

    public int id;
    private String email;
    private String password;

    public UserData() {}

    public UserData(int id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public UserData(int id, String email) {
        this.id = id;
        this.email = email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getID() {
        return this.id;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    @NonNull
    @Override
    public String toString() {
        return "User data{" +
                "id= " + this.id +
                "\nemail= " + this.email;
    }

}
