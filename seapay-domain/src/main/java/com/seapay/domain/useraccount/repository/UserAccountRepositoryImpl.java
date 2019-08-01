package com.seapay.domain.useraccount.repository;

import com.seapay.common.repository.Repository;
import com.seapay.domain.useraccount.UserAccountRepository;
import com.seapay.domain.useraccount.entity.UserAccount;

public class UserAccountRepositoryImpl extends Repository<UserAccountDbInterface> implements UserAccountRepository {

    @Override
    public Long insert(UserAccount userAccount) {
        return withDBInterface(UserAccountDbInterface.class, repository -> repository.insert(userAccount));
    }

    @Override
    public UserAccount get(Long ID) {
        return withDBInterface(UserAccountDbInterface.class, repository -> repository.get(ID));
    }

    @Override
    public UserAccount getByUserId(String userID) {
        return withDBInterface(UserAccountDbInterface.class, repository -> repository.getByUserId(userID));
    }

    @Override
    public UserAccount getByEmail(String email) {
        return withDBInterface(UserAccountDbInterface.class, repository -> repository.getByEmail(email));
    }
}
