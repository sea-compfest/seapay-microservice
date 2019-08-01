package com.seapay.domain.useraccount;

import com.seapay.domain.useraccount.entity.UserAccount;

public interface UserAccountRepository {
    Long insert(UserAccount user);
    UserAccount get(Long ID);
    UserAccount getByUserId(String userID);
    UserAccount getByEmail(String email);
}
