package com.seapay.domain.useraccount.repository;

import com.seapay.domain.useraccount.UserAccountRepository;
import com.seapay.domain.useraccount.entity.UserAccount;
import com.seapay.domain.useraccount.entity.UserAccountBuilder;
import com.seapay.domain.utils.DatabaseUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserAccountRepositoryImplTest {

    private UserAccountRepository userAccountRepository = new UserAccountRepositoryImpl();

    @BeforeEach
    void setUp() {
        DatabaseUtils.truncate("user_accounts");
    }

    @Test
    void insert() {
        String userID = UUID.randomUUID().toString();
        UserAccount userAccount = UserAccountBuilder.anUserAccount()
            .withName("fulan")
            .withEmail("fulan@seapay.co.id")
            .withPhonenumber("081108110811")
            .withUserID(userID)
            .withUserType(0)
            .build();

        userAccountRepository.insert(userAccount);

        UserAccount actual = userAccountRepository.getByUserId(userID);

        assertEquals(userAccount.getName(), actual.getName());
        assertEquals(userAccount.getUserID(), actual.getUserID());
        assertEquals(userAccount.getEmail(), actual.getEmail());
        assertEquals(userAccount.getPhonenumber(), actual.getPhonenumber());
        assertEquals(userAccount.getUserType(), actual.getUserType());
    }
}