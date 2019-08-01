package com.seapay.domain.transaction.repository;

import com.seapay.domain.transaction.entity.Transaction;
import com.seapay.domain.transaction.entity.TransactionBuilder;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionMapper implements ResultSetMapper<Transaction> {
    @Override
    public Transaction map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        Transaction user = TransactionBuilder.aTransaction()
            .withId(r.getLong("id"))
            .withTransactionID(r.getString("transaction_id"))
            .withReferenceID(r.getString("reference_id"))
            .withCreditedWallet(r.getString("credited_wallet"))
            .withDebitedWallet(r.getString("debited_wallet"))
            .withDescription(r.getString("description"))
            .withTransactionDate(r.getTimestamp("transaction_date"))
            .withAmount(r.getLong("amount"))
            .withTransactionType(r.getInt("transaction_type"))
            .build();

        return user;
    }
}
