package com.seapay.domain.transaction.entity;

import java.sql.Timestamp;
import java.time.ZonedDateTime;

public final class TransactionBuilder {
    private Long id;
    private String transactionID;
    private String referenceID;
    private String creditedWallet;
    private String debitedWallet;
    private String description;
    private Timestamp transactionDate;
    private Long amount;
    private int transactionType;

    private TransactionBuilder() {
    }

    public static TransactionBuilder aTransaction() {
        return new TransactionBuilder();
    }

    public TransactionBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public TransactionBuilder withTransactionID(String transactionID) {
        this.transactionID = transactionID;
        return this;
    }

    public TransactionBuilder withReferenceID(String referenceID) {
        this.referenceID = referenceID;
        return this;
    }

    public TransactionBuilder withCreditedWallet(String creditedWallet) {
        this.creditedWallet = creditedWallet;
        return this;
    }

    public TransactionBuilder withDebitedWallet(String debitedWallet) {
        this.debitedWallet = debitedWallet;
        return this;
    }

    public TransactionBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public TransactionBuilder withTransactionDate(Timestamp transactionDate) {
        this.transactionDate = transactionDate;
        return this;
    }

    public TransactionBuilder withAmount(Long amount) {
        this.amount = amount;
        return this;
    }

    public TransactionBuilder withTransactionType(int transactionType) {
        this.transactionType = transactionType;
        return this;
    }

    public Transaction build() {
        return new Transaction(id, transactionID, referenceID, creditedWallet, debitedWallet, description, transactionDate, amount, transactionType);
    }
}
