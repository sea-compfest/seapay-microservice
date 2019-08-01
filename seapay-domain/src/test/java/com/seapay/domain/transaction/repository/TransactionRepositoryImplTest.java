package com.seapay.domain.transaction.repository;

import com.seapay.domain.transaction.TransactionRepository;
import com.seapay.domain.transaction.entity.Transaction;
import com.seapay.domain.transaction.entity.TransactionBuilder;
import com.seapay.domain.utils.DatabaseUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class TransactionRepositoryImplTest {

    private TransactionRepository transactionRepository = new TransactionRepositoryImpl();

    @BeforeEach
    void setUp() {
        DatabaseUtils.truncate("transactions");
    }

    @Test
    void insert() {
        String transactionID = UUID.randomUUID().toString();
        Transaction transaction = TransactionBuilder.aTransaction()
            .withTransactionID(transactionID)
            .withReferenceID(UUID.randomUUID().toString())
            .withCreditedWallet(UUID.randomUUID().toString())
            .withDebitedWallet(UUID.randomUUID().toString())
            .withDescription("topup for seapay")
            .withTransactionDate(Timestamp.valueOf(LocalDateTime.now()))
            .withAmount(10L)
            .withTransactionType(0)
            .build();

        transactionRepository.insert(transaction);

        Transaction actual = transactionRepository.getByTransactionID(transactionID);

        assertEquals(transaction.getTransactionID(), actual.getTransactionID());
        assertEquals(transaction.getReferenceID(), actual.getReferenceID());
        assertEquals(transaction.getCreditedWallet(), actual.getCreditedWallet());
        assertEquals(transaction.getDebitedWallet(), actual.getDebitedWallet());
        assertEquals(transaction.getDescription(), actual.getDescription());
        assertEquals(transaction.getTransactionDate(), actual.getTransactionDate());
        assertEquals(transaction.getAmount(), actual.getAmount());
        assertEquals(transaction.getTransactionType(), actual.getTransactionType());

    }
}