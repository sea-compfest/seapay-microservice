package com.seapay.api.gateway.request;

import com.google.gson.annotations.SerializedName;

public class TopupRequest {

    @SerializedName("user_id")
    private String userID;

    private Long amount;

    @SerializedName("reference_id")
    private String referenceID;

    private String description;

    public TopupRequest(String userID, Long amount, String referenceID, String description) {
        this.userID = userID;
        this.amount = amount;
        this.referenceID = referenceID;
        this.description = description;
    }

    public String getUserID() {
        return userID;
    }

    public Long getAmount() {
        return amount;
    }

    public String getReferenceID() {
        return referenceID;
    }

    public String getDescription() {
        return description;
    }
}
