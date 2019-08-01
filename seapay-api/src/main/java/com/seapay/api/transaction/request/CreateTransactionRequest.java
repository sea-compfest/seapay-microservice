package com.seapay.api.transaction.request;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;

public class CreateTransactionRequest {
    @SerializedName("transaction_id")
    private String transactionID;

    @SerializedName("reference_id")
    private String referenceID;

    @SerializedName("credited_wallet")
    private String creditedWallet;

    @SerializedName("debited_wallet")
    private String debitedWallet;

    private String description;

    @SerializedName("transaction_date")
    private LocalDateTime transactionDate;

    private Long amount;

    @SerializedName("transaction_type")
    private int transactionType;

    public CreateTransactionRequest(String transactionID, String referenceID, String creditedWallet, String debitedWallet, String description, LocalDateTime transactionDate, Long amount, int transactionType) {
        this.transactionID = transactionID;
        this.referenceID = referenceID;
        this.creditedWallet = creditedWallet;
        this.debitedWallet = debitedWallet;
        this.description = description;
        this.transactionDate = transactionDate;
        this.amount = amount;
        this.transactionType = transactionType;
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

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public Long getAmount() {
        return amount;
    }

    public int getTransactionType() {
        return transactionType;
    }

    public static final class CreateTransactionRequestBuilder {
        private String transactionID;
        private String referenceID;
        private String creditedWallet;
        private String debitedWallet;
        private String description;
        private LocalDateTime transactionDate;
        private Long amount;
        private int transactionType;

        private CreateTransactionRequestBuilder() {
        }

        public static CreateTransactionRequestBuilder aCreateTransactionRequest() {
            return new CreateTransactionRequestBuilder();
        }

        public CreateTransactionRequestBuilder withTransactionID(String transactionID) {
            this.transactionID = transactionID;
            return this;
        }

        public CreateTransactionRequestBuilder withReferenceID(String referenceID) {
            this.referenceID = referenceID;
            return this;
        }

        public CreateTransactionRequestBuilder withCreditedWallet(String creditedWallet) {
            this.creditedWallet = creditedWallet;
            return this;
        }

        public CreateTransactionRequestBuilder withDebitedWallet(String debitedWallet) {
            this.debitedWallet = debitedWallet;
            return this;
        }

        public CreateTransactionRequestBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        public CreateTransactionRequestBuilder withTransactionDate(LocalDateTime transactionDate) {
            this.transactionDate = transactionDate;
            return this;
        }

        public CreateTransactionRequestBuilder withAmount(Long amount) {
            this.amount = amount;
            return this;
        }

        public CreateTransactionRequestBuilder withTransactionType(int transactionType) {
            this.transactionType = transactionType;
            return this;
        }

        public CreateTransactionRequest build() {
            return new CreateTransactionRequest(transactionID, referenceID, creditedWallet, debitedWallet, description, transactionDate, amount, transactionType);
        }
    }
}
