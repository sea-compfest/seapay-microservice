package com.seapay.domain.wallet.repository;

import com.seapay.common.repository.Repository;
import com.seapay.domain.wallet.WalletRepository;
import com.seapay.domain.wallet.entity.Wallet;

public class WalletRepositoryImpl extends Repository<WalletDbInterface> implements WalletRepository {

    @Override
    public Long insert(Wallet wallet) {
        return withDBInterface(WalletDbInterface.class, repository -> repository.insert(wallet));
    }

    @Override
    public Wallet get(Long ID) {
        return withDBInterface(WalletDbInterface.class, repository -> repository.get(ID));
    }

    @Override
    public Wallet getByWalletID(String walletID) {
        return withDBInterface(WalletDbInterface.class, repository -> repository.getByWalletID(walletID));
    }

    @Override
    public Wallet getByUserID(String userID) {
        return withDBInterface(WalletDbInterface.class, repository -> repository.getByUserID(userID));
    }

    @Override
    public void updateBalance(Wallet wallet) {
        withDBInterface(WalletDbInterface.class, repository -> repository.updateBalance(wallet.getWalletID(), wallet.getBalance()));
    }
}
