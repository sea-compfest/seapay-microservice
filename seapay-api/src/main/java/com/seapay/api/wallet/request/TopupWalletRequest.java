package com.seapay.api.wallet.request;

import com.google.gson.annotations.SerializedName;

public class TopupWalletRequest {
    @SerializedName("wallet_id")
    private String walletID;

    private Long amount;

    public TopupWalletRequest(String walletID, Long amount) {
        this.walletID = walletID;
        this.amount = amount;
    }

    public String getWalletID() {
        return walletID;
    }

    public Long getAmount() {
        return amount;
    }
}
