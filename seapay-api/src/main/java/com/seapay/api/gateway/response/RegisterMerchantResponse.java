package com.seapay.api.gateway.response;

import com.google.gson.annotations.SerializedName;

public class RegisterMerchantResponse {

    @SerializedName("merchant_id")
    private String merchantID;

    private String name;

    private String email;

    public RegisterMerchantResponse(String merchantID, String name, String email) {
        this.merchantID = merchantID;
        this.name = name;
        this.email = email;
    }

    public String getUserID() {
        return merchantID;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
