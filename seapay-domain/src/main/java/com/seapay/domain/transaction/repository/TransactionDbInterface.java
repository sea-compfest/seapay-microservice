package com.seapay.domain.transaction.repository;

import com.seapay.domain.transaction.entity.Transaction;
import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

@RegisterMapper(TransactionMapper.class)
public interface TransactionDbInterface {

    @SqlUpdate("INSERT INTO transactions (transaction_id, reference_id, credited_wallet, debited_wallet, description, transaction_date, amount, transaction_type) VALUES (:w.transactionID, :w.referenceID, :w.creditedWallet, :w.debitedWallet, :w.description, :w.transactionDate, :w.amount, :w.transactionType)")
    @GetGeneratedKeys
    long insert(@BindBean("w") Transaction userAccount);

    @SqlQuery("SELECT id, transaction_id, reference_id, credited_wallet, debited_wallet, description, transaction_date, amount, transaction_type FROM transactions WHERE ID = :ID")
    Transaction get(@Bind("ID") Long ID);

    @SqlQuery("SELECT id, transaction_id, reference_id, credited_wallet, debited_wallet, description, transaction_date, amount, transaction_type FROM transactions WHERE transaction_id = :ID")
    Transaction getByTransactionID(@Bind("ID") String transactionID);
}
