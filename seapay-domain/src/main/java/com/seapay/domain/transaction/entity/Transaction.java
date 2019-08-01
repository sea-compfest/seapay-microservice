package com.seapay.domain.transaction.entity;

import java.sql.Timestamp;

public class Transaction {

    private Long id;

    private String transactionID;

    private String referenceID;

    private String creditedWallet;

    private String debitedWallet;

    private String description;

    private Timestamp transactionDate;

    private Long amount;

    private int transactionType;

    public Transaction(Long id, String transactionID, String referenceID, String creditedWallet, String debitedWallet, String description, Timestamp transactionDate, Long amount, int transactionType) {
        this.id = id;
        this.transactionID = transactionID;
        this.referenceID = referenceID;
        this.creditedWallet = creditedWallet;
        this.debitedWallet = debitedWallet;
        this.description = description;
        this.transactionDate = transactionDate;
        this.amount = amount;
        this.transactionType = transactionType;
    }

    public Long getId() {
        return id;
    }

    public String getTransactionID() {
        return transactionID;
    }

    public String getReferenceID() {
        return referenceID;
    }

    public String getCreditedWallet() {
        return creditedWallet;
    }

    public String getDebitedWallet() {
        return debitedWallet;
    }

    public String getDescription() {
        return description;
    }

    public Timestamp getTransactionDate() {
        return transactionDate;
    }

    public Long getAmount() {
        return amount;
    }

    public int getTransactionType() {
        return transactionType;
    }
}
