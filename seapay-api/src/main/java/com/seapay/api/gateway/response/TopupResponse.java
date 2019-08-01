package com.seapay.api.gateway.response;

import com.google.gson.annotations.SerializedName;

public class TopupResponse {

    @SerializedName("transaction_id")
    private String transactionID;

    @SerializedName("merchant_id")
    private String userID;

    private Long amount;

    @SerializedName("reference_id")
    private String referenceID;

    private String description;

    public TopupResponse(String transactionID, String userID, Long amount, String referenceID, String description) {
        this.transactionID = transactionID;
        this.userID = userID;
        this.amount = amount;
        this.referenceID = referenceID;
        this.description = description;
    }

    public String getTransactionID() {
        return transactionID;
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
