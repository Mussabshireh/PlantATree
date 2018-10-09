package com.treeshopapp.treeshop;

public class Users
{
    String userID;
    String userName;
    String email;
    String address;

    public Users()
    {


    }

    public Users(String userID, String userName, String email, String address) {
        this.userID = userID;
        this.userName = userName;
        this.email = email;
        this.address = address;
    }

    public String getUserID() {
        return userID;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }
}
