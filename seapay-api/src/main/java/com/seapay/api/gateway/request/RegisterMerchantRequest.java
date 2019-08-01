package com.seapay.api.gateway.request;

public class RegisterMerchantRequest {
    private String name;
    private String email;

    public RegisterMerchantRequest(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
