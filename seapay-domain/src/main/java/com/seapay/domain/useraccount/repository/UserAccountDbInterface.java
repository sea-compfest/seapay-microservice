package com.seapay.domain.useraccount.repository;

import com.seapay.domain.useraccount.entity.UserAccount;
import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

@RegisterMapper(UserAccountMapper.class)
public interface UserAccountDbInterface {

    @SqlUpdate("INSERT INTO user_accounts (user_id, name, email, phonenumber, user_type) VALUES (:w.userID, :w.name, :w.email, :w.phonenumber, :w.userType)")
    @GetGeneratedKeys
    long insert(@BindBean("w") UserAccount userAccount);

    @SqlQuery("SELECT id, user_id, name, email, phonenumber, user_type FROM user_accounts WHERE ID = :ID")
    UserAccount get(@Bind("ID") Long ID);

    @SqlQuery("SELECT id, user_id, name, email, phonenumber, user_type FROM user_accounts WHERE user_id = :ID")
    UserAccount getByUserId(@Bind("ID") String userID);

    @SqlQuery("SELECT id, user_id, name, email, phonenumber, user_type FROM user_accounts WHERE email = :EMAIL")
    UserAccount getByEmail(@Bind("EMAIL") String email);
}
