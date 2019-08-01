package com.seapay;


import com.seapay.api.gateway.GatewayService;
import com.seapay.api.transaction.TransactionService;
import com.seapay.api.useraccount.UserAccountService;
import com.seapay.api.wallet.WalletService;
import com.seapay.client.TransactionServiceClient;
import com.seapay.client.UserServiceClient;
import com.seapay.client.WalletServiceClient;
import com.seapay.domain.gateway.GatewayServiceImpl;
import com.seapay.server.Router;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GatewayServiceApplication {
    private static final Logger logger = LoggerFactory.getLogger(GatewayServiceApplication.class);

    public static void main(String[] args) {
        logger.info("Starting seapay monolith application");


        WalletService walletService = new WalletServiceClient();

        UserAccountService userAccountService = new UserServiceClient();

        TransactionService transactionService = new TransactionServiceClient();

        GatewayService gatewayService = new GatewayServiceImpl(userAccountService, walletService, transactionService);

        Router router = new Router(gatewayService);
        router.configure();

        logger.info("server is running");
    }
}
