package com.seapay;

import com.seapay.api.transaction.TransactionService;
import com.seapay.domain.transaction.TransactionRepository;
import com.seapay.domain.transaction.TransactionServiceImpl;
import com.seapay.domain.transaction.repository.TransactionRepositoryImpl;
import com.seapay.server.Router;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TransactionServiceApplication {
    private static final Logger logger = LoggerFactory.getLogger(TransactionServiceApplication.class);

    public static void main(String[] args) {
        logger.info("Starting seapay transaction-service application");

        TransactionRepository transactionRepository = new TransactionRepositoryImpl();
        TransactionService transactionService = new TransactionServiceImpl(transactionRepository);
        Router router = new Router(transactionService);
        router.configure();

        logger.info("server is running");
    }
}
