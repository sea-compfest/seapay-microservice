package com.seapay.domain.useraccount.service;

import com.seapay.api.useraccount.UserAccountService;
import com.seapay.api.useraccount.request.CreateUserAccountRequest;
import com.seapay.domain.useraccount.UserAccountRepository;
import com.seapay.domain.useraccount.UserAccountServiceImpl;
import com.seapay.domain.useraccount.entity.UserAccount;
import com.seapay.domain.useraccount.repository.UserAccountRepositoryImpl;
import com.seapay.domain.utils.DatabaseUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static com.seapay.domain.constant.SeaPayConstant.CUSTOMER_TYPE;
import static org.junit.jupiter.api.Assertions.assertEquals;

class UserAccountServiceImplTest {

    private UserAccountRepository userAccountRepository = new UserAccountRepositoryImpl();
    private UserAccountService userAccountService = new UserAccountServiceImpl(userAccountRepository);

    @BeforeEach
    void setUp() {
        DatabaseUtils.truncate("user_accounts");
    }

    @Test
    void createAccountTest() {
        String userID = UUID.randomUUID().toString();
        String name = "test";
        String email = "test@seapay.co.id";
        String phonenumber = "0000000000";
        int userType = CUSTOMER_TYPE;

        CreateUserAccountRequest createUserAccountRequest = CreateUserAccountRequest.CreateUserAccountRequestBuilder.aCreateUserAccountRequest()
                .withUserID(userID)
                .withEmail(email)
                .withName(name)
                .withPhonenumber(phonenumber)
                .withUserType(userType)
                .build();

        userAccountService.createAccount(createUserAccountRequest);

        UserAccount actual = userAccountRepository.getByUserId(userID);

        assertEquals(name, actual.getName());
        assertEquals(userID, actual.getUserID());
        assertEquals(email, actual.getEmail());
        assertEquals(phonenumber, actual.getPhonenumber());
        assertEquals(userType, actual.getUserType());
    }
}