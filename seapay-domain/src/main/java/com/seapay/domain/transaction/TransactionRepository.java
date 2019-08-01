package com.seapay.domain.transaction;

import com.seapay.domain.transaction.entity.Transaction;

public interface TransactionRepository {
    Long insert(Transaction user);
    Transaction get(Long ID);
    Transaction getByTransactionID(String transactionID);
}
