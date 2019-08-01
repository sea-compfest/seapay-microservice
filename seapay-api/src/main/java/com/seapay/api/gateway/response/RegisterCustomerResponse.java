package com.seapay.api.gateway.response;

import com.google.gson.annotations.SerializedName;

public class RegisterCustomerResponse {

    @SerializedName("user_id")
    private String userID;

    private String name;

    private String email;

    private String phonenumber;

    public RegisterCustomerResponse(String userID, String name, String email, String phonenumber) {
        this.userID = userID;
        this.name = name;
        this.email = email;
        this.phonenumber = phonenumber;
    }

    public String getUserID() {
        return userID;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }
}
