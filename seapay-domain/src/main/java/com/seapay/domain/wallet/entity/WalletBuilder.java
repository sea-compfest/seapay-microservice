package com.seapay.domain.wallet.entity;

public final class WalletBuilder {
    private Long id;
    private String walletID;
    private String userID;
    private Long balance;

    private WalletBuilder() {
    }

    public static WalletBuilder aWallet() {
        return new WalletBuilder();
    }

    public WalletBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public WalletBuilder withWalletID(String walletID) {
        this.walletID = walletID;
        return this;
    }

    public WalletBuilder withUserID(String userID) {
        this.userID = userID;
        return this;
    }

    public WalletBuilder withBalance(Long balance) {
        this.balance = balance;
        return this;
    }

    public Wallet build() {
        return new Wallet(id, walletID, userID, balance);
    }
}
