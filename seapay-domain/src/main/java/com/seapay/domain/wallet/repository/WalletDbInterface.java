package com.seapay.domain.wallet.repository;

import com.seapay.domain.wallet.entity.Wallet;
import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.sql.Timestamp;

@RegisterMapper(WalletMapper.class)
public interface WalletDbInterface {

    @SqlUpdate("INSERT INTO wallets (wallet_id, user_id, balance) VALUES (:w.walletID, :w.userID, :w.balance)")
    @GetGeneratedKeys
    long insert(@BindBean("w") Wallet wallet);

    @SqlQuery("SELECT id, wallet_id, user_id, balance FROM wallets WHERE ID = :ID")
    Wallet get(@Bind("ID") Long ID);

    @SqlQuery("SELECT id, wallet_id, user_id, balance FROM wallets WHERE wallet_id = :ID")
    Wallet getByWalletID(@Bind("ID") String walletID);

    @SqlQuery("SELECT id, wallet_id, user_id, balance FROM wallets WHERE user_id = :ID")
    Wallet getByUserID(@Bind("ID") String userID);

    @SqlUpdate("UPDATE wallets SET balance = :newBalance where wallet_id = :walletId")
    int updateBalance(@Bind("walletId") String walletId, @Bind("newBalance") Long newBalance);
}
