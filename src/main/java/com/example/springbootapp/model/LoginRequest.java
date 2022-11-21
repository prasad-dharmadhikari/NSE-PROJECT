package com.example.springbootapp.model;

public class LoginRequest {

    public String username;
    public String password;

    public LoginRequest(String username, String password) {
        //super();
        this.username = username;
        this.password = password;
    }

    public LoginRequest() {
        //super();
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginRequest [username=" + username + ", password=" + password + "]";
    }
    
}
