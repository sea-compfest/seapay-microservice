package com.seapay.domain.useraccount;

import com.seapay.api.useraccount.UserAccountService;
import com.seapay.api.useraccount.request.CreateUserAccountRequest;
import com.seapay.domain.useraccount.entity.UserAccount;
import com.seapay.domain.useraccount.entity.UserAccountBuilder;

public class UserAccountServiceImpl implements UserAccountService {

    private UserAccountRepository userAccountRepository;

    public UserAccountServiceImpl(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    @Override
    public void createAccount(CreateUserAccountRequest request) {
        UserAccount userAccount = UserAccountBuilder.anUserAccount()
            .withEmail(request.getEmail())
            .withName(request.getName())
            .withUserID(request.getUserID())
            .withPhonenumber(request.getPhonenumber())
            .withUserType(request.getUserType())
            .build();

        userAccountRepository.insert(userAccount);
    }
}
