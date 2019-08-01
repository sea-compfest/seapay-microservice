package com.seapay.domain.wallet.repository;

import com.seapay.domain.utils.DatabaseUtils;
import com.seapay.domain.wallet.WalletRepository;
import com.seapay.domain.wallet.entity.Wallet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WalletRepositoryImplTest {

    private WalletRepository walletRepository = new WalletRepositoryImpl();

    @BeforeEach
    void setUp() {
        DatabaseUtils.truncate("wallets");
    }

    @Test
    void testInsertAndGet() {
        String walletID = UUID.randomUUID().toString();
        Wallet wallet = new Wallet(walletID, UUID.randomUUID().toString());

        walletRepository.insert(wallet);

        Wallet actual = walletRepository.getByWalletID(walletID);

        assertEquals(wallet.getWalletID(), actual.getWalletID());
        assertEquals(wallet.getUserID(), actual.getUserID());
        assertEquals(wallet.getBalance(), actual.getBalance());
    }

    @Test
    void testGetByUserId() {
        String userID = UUID.randomUUID().toString();
        Wallet wallet = new Wallet(UUID.randomUUID().toString(), userID);

        walletRepository.insert(wallet);

        Wallet actual = walletRepository.getByUserID(userID);

        assertEquals(wallet.getWalletID(), actual.getWalletID());
        assertEquals(wallet.getUserID(), actual.getUserID());
        assertEquals(wallet.getBalance(), actual.getBalance());
    }
}