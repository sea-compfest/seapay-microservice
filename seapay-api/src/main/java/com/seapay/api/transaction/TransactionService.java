package com.seapay.api.transaction;

import com.seapay.api.transaction.request.CreateTransactionRequest;

public interface TransactionService {
    void createTransaction(CreateTransactionRequest request);
}
