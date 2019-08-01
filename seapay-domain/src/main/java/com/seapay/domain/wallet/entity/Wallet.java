package com.seapay.domain.wallet.entity;

public class Wallet {

    private Long id;

    private String walletID;

    private String userID;

    private Long balance;

    public Wallet(String walletID, String userID) {
        this.walletID = walletID;
        this.userID = userID;
        this.balance = 0L;
    }

    public Wallet(Long id, String walletID, String userID, Long balance) {
        this.walletID = walletID;
        this.userID = userID;
        this.balance = balance;
        this.id = id;
    }

    public void addBalance(Long balance) {
        this.balance =+ balance;
    }

    public void substractBalance(Long balance) {
        this.balance =- balance;
    }

    public Long getId() {
        return id;
    }

    public String getWalletID() {
        return walletID;
    }

    public String getUserID() {
        return userID;
    }

    public Long getBalance() {
        return balance;
    }


}
