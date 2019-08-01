package com.seapay.domain.transaction.service;

import com.seapay.api.transaction.TransactionService;
import com.seapay.api.transaction.request.CreateTransactionRequest;
import com.seapay.common.marshalling.Json;
import com.seapay.domain.transaction.TransactionRepository;
import com.seapay.domain.transaction.TransactionServiceImpl;
import com.seapay.domain.transaction.entity.Transaction;
import com.seapay.domain.transaction.entity.TransactionBuilder;
import com.seapay.domain.transaction.repository.TransactionRepositoryImpl;
import com.seapay.domain.utils.DatabaseUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

import static com.seapay.domain.constant.SeaPayConstant.TOPUP_TRANSACTION;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TransactionServiceImplTest {

    private TransactionRepository transactionRepository = new TransactionRepositoryImpl();
    private TransactionService transactionService = new TransactionServiceImpl(transactionRepository);

    @BeforeEach
    void setUp() {
        DatabaseUtils.truncate("transactions");
    }

    @Test
    void createTransactionTest() {
        String transactionID = UUID.randomUUID().toString();
        String referenceID = UUID.randomUUID().toString();
        String creditedWallet = UUID.randomUUID().toString();
        String debitedWallet = UUID.randomUUID().toString();
        String description = "this is a description";
        LocalDateTime transactionDate = LocalDateTime.now();
        Long amount = 3000L;
        int transactionType = TOPUP_TRANSACTION;

        CreateTransactionRequest createTransactionRequest = CreateTransactionRequest.CreateTransactionRequestBuilder.aCreateTransactionRequest()
                .withTransactionID(transactionID)
                .withReferenceID(referenceID)
                .withCreditedWallet(creditedWallet)
                .withDebitedWallet(debitedWallet)
                .withDescription(description)
                .withTransactionDate(transactionDate)
                .withAmount(amount)
                .withTransactionType(transactionType)
                .build();

        transactionService.createTransaction(createTransactionRequest);

        Transaction actual = transactionRepository.getByTransactionID(transactionID);

        assertEquals(transactionID, actual.getTransactionID());
        assertEquals(referenceID, actual.getReferenceID());
        assertEquals(creditedWallet, actual.getCreditedWallet());
        assertEquals(debitedWallet, actual.getDebitedWallet());
        assertEquals(Timestamp.valueOf(transactionDate), actual.getTransactionDate());
        assertEquals(amount, actual.getAmount());
        assertEquals(transactionType, actual.getTransactionType());
    }
}