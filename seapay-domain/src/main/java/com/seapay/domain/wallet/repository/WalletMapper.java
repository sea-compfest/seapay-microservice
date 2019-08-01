package com.seapay.domain.wallet.repository;

import com.seapay.domain.wallet.entity.Wallet;
import com.seapay.domain.wallet.entity.WalletBuilder;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WalletMapper implements ResultSetMapper<Wallet> {

    @Override
    public Wallet map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        Wallet wallet = WalletBuilder.aWallet()
            .withId(r.getLong("id"))
            .withWalletID(r.getString("wallet_id"))
            .withUserID(r.getString("user_id"))
            .withBalance(r.getLong("balance"))
            .build();

        return wallet;
    }
}
