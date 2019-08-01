package com.seapay.api.gateway.response;

import com.google.gson.annotations.SerializedName;

public class PayResponse {

    @SerializedName("transaction_id")
    private String transactionID;

    @SerializedName("user_id")
    private String userID;

    @SerializedName("merchant_id")
    private String merchantID;

    private Long amount;

    @SerializedName("reference_id")
    private String referenceID;

    private String description;

    public PayResponse(String transactionID, String userID, String merchantID, Long amount, String referenceID, String description) {
        this.transactionID = transactionID;
        this.userID = userID;
        this.merchantID = merchantID;
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

    public String getMerchantID() {
        return merchantID;
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
