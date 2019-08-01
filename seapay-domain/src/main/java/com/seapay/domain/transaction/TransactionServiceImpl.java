package com.seapay.domain.transaction;

import com.seapay.api.transaction.TransactionService;
import com.seapay.api.transaction.request.CreateTransactionRequest;
import com.seapay.domain.transaction.entity.Transaction;
import com.seapay.domain.transaction.entity.TransactionBuilder;

import java.sql.Timestamp;

public class TransactionServiceImpl implements TransactionService {

    private TransactionRepository transactionRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public void createTransaction(CreateTransactionRequest request) {
        Transaction transaction = TransactionBuilder.aTransaction()
            .withTransactionID(request.getTransactionID())
            .withReferenceID(request.getReferenceID())
            .withCreditedWallet(request.getCreditedWallet())
            .withDebitedWallet(request.getDebitedWallet())
            .withDescription(request.getDescription())
            .withTransactionDate(Timestamp.valueOf(request.getTransactionDate()))
            .withAmount(request.getAmount())
            .withTransactionType(request.getTransactionType())
            .build();

        this.transactionRepository.insert(transaction);
    }
}
