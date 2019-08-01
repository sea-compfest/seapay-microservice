package com.seapay;

import com.seapay.api.gateway.GatewayService;
import com.seapay.api.transaction.TransactionService;
import com.seapay.api.useraccount.UserAccountService;
import com.seapay.api.wallet.WalletService;
import com.seapay.domain.gateway.GatewayServiceImpl;
import com.seapay.domain.transaction.TransactionRepository;
import com.seapay.domain.transaction.TransactionServiceImpl;
import com.seapay.domain.transaction.repository.TransactionRepositoryImpl;
import com.seapay.domain.useraccount.UserAccountRepository;
import com.seapay.domain.useraccount.UserAccountServiceImpl;
import com.seapay.domain.useraccount.repository.UserAccountRepositoryImpl;
import com.seapay.domain.wallet.WalletRepository;
import com.seapay.domain.wallet.WalletServiceImpl;
import com.seapay.domain.wallet.repository.WalletRepositoryImpl;
import com.seapay.server.Router;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SeaPayApplication {
    private static final Logger logger = LoggerFactory.getLogger(SeaPayApplication.class);

    public static void main(String[] args) {
        logger.info("Starting seapay monolith application");

        WalletRepository walletRepository = new WalletRepositoryImpl();

        UserAccountRepository userAccountRepository = new UserAccountRepositoryImpl();

        TransactionRepository transactionRepository = new TransactionRepositoryImpl();

        WalletService walletService = new WalletServiceImpl(walletRepository);

        UserAccountService userAccountService = new UserAccountServiceImpl(userAccountRepository);

        TransactionService transactionService = new TransactionServiceImpl(transactionRepository);

        GatewayService gatewayService = new GatewayServiceImpl(userAccountService, walletService, transactionService);

        Router router = new Router(gatewayService);
        router.configure();

        logger.info("server is running");
    }
}
