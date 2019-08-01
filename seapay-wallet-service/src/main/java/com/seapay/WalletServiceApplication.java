package com.seapay;

import com.seapay.api.wallet.WalletService;
import com.seapay.domain.wallet.WalletRepository;
import com.seapay.domain.wallet.WalletServiceImpl;
import com.seapay.domain.wallet.repository.WalletRepositoryImpl;
import com.seapay.server.Router;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WalletServiceApplication {
    private static final Logger logger = LoggerFactory.getLogger(WalletServiceApplication.class);

    public static void main(String[] args) {
        logger.info("Starting seapay wallet-service application");

        WalletRepository walletRepository = new WalletRepositoryImpl();
        WalletService walletService = new WalletServiceImpl(walletRepository);
        Router router = new Router(walletService);
        router.configure();

        logger.info("server is running");
    }
}
