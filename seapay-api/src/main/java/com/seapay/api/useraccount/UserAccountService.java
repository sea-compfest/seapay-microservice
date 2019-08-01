package com.seapay.api.useraccount;

import com.seapay.api.useraccount.request.CreateUserAccountRequest;

public interface UserAccountService {
    void createAccount(CreateUserAccountRequest request);
}
