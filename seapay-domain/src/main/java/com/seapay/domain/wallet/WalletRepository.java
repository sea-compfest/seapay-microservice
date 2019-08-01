package com.seapay.domain.wallet;

import com.seapay.domain.wallet.entity.Wallet;

public interface WalletRepository {
    Long insert(Wallet wallet);
    Wallet get(Long ID);
    Wallet getByWalletID(String walletID);
    Wallet getByUserID(String userID);
    void updateBalance(Wallet wallet);
}
