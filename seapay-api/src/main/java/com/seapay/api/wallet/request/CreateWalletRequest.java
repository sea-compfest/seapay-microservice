package com.seapay.api.wallet.request;

import com.google.gson.annotations.SerializedName;

public class CreateWalletRequest {
    @SerializedName("user_id")
    private String userID;

    @SerializedName("wallet_id")
    private String walletID;

    public String getUserID() {
        return userID;
    }

    public String getWalletID() {
        return walletID;
    }

    public CreateWalletRequest(String userID, String walletID) {
        this.userID = userID;
        this.walletID = walletID;
    }


    public static final class CreateWalletRequestBuilder {
        private String userID;
        private String walletID;

        private CreateWalletRequestBuilder() {
        }

        public static CreateWalletRequestBuilder aCreateWalletRequest() {
            return new CreateWalletRequestBuilder();
        }

        public CreateWalletRequestBuilder withUserID(String userID) {
            this.userID = userID;
            return this;
        }

        public CreateWalletRequestBuilder withWalletID(String walletID) {
            this.walletID = walletID;
            return this;
        }

        public CreateWalletRequest build() {
            return new CreateWalletRequest(userID, walletID);
        }
    }
}
