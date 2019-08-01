package com.seapay.api.gateway.request;

public class RegisterCustomerRequest {
    private String name;
    private String email;
    private String phonenumber;

    public RegisterCustomerRequest(String name, String email, String phonenumber) {
        this.name = name;
        this.email = email;
        this.phonenumber = phonenumber;
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
