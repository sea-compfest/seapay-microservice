package com.seapay.domain.useraccount.repository;

import com.seapay.domain.useraccount.entity.UserAccount;
import com.seapay.domain.useraccount.entity.UserAccountBuilder;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserAccountMapper implements ResultSetMapper<UserAccount> {
    @Override
    public UserAccount map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        UserAccount user = UserAccountBuilder.anUserAccount()
            .withId(r.getLong("id"))
            .withUserID(r.getString("user_id"))
            .withName(r.getString("name"))
            .withEmail(r.getString("email"))
            .withPhonenumber(r.getString("phonenumber"))
            .withUserType(r.getInt("user_type"))
            .build();

        return user;
    }
}
