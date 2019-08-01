package com.seapay.api.wallet.request;

import com.google.gson.annotations.SerializedName;

public class TransferRequest {

    @SerializedName("source_id")
    private String sourceID;

    @SerializedName("target_id")
    private String targetID;

    private Long amount;

    public TransferRequest(String sourceID, String targetID, Long amount) {
        this.sourceID = sourceID;
        this.targetID = targetID;
        this.amount = amount;
    }

    public String getSourceID() {
        return sourceID;
    }

    public String getTargetID() {
        return targetID;
    }

    public Long getAmount() {
        return amount;
    }
}
