package com.seapay.domain.transaction.repository;

import com.seapay.common.repository.Repository;
import com.seapay.domain.transaction.TransactionRepository;
import com.seapay.domain.transaction.entity.Transaction;

public class TransactionRepositoryImpl extends Repository<TransactionDbInterface> implements TransactionRepository {

    @Override
    public Long insert(Transaction transaction) {
        return withDBInterface(TransactionDbInterface.class, repository -> repository.insert(transaction));
    }

    @Override
    public Transaction get(Long ID) {
        return withDBInterface(TransactionDbInterface.class, repository -> repository.get(ID));
    }

    @Override
    public Transaction getByTransactionID(String transactionID) {
        return withDBInterface(TransactionDbInterface.class, repository -> repository.getByTransactionID(transactionID));
    }
}
