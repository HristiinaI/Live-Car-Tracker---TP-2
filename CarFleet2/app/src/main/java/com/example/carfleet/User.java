package com.example.carfleet;

public class User {
    public String id;
    public String userName;
    public String email;
    public String password;

    public User(String id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

}
