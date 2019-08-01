package com.seapay;

import com.seapay.api.useraccount.UserAccountService;
import com.seapay.domain.useraccount.UserAccountRepository;
import com.seapay.domain.useraccount.UserAccountServiceImpl;
import com.seapay.domain.useraccount.repository.UserAccountRepositoryImpl;
import com.seapay.server.Router;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserServiceApplication {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceApplication.class);

    public static void main(String[] args) {
        logger.info("Starting seapay user-service application");

        UserAccountRepository userAccountRepository = new UserAccountRepositoryImpl();
        UserAccountService userAccountService = new UserAccountServiceImpl(userAccountRepository);

        Router router = new Router(userAccountService);
        router.configure();

        logger.info("server is running");
    }
}
