package com.seapay.domain.wallet.service;

import com.seapay.api.wallet.WalletService;
import com.seapay.api.wallet.request.CreateWalletRequest;
import com.seapay.api.wallet.request.TopupWalletRequest;
import com.seapay.api.wallet.request.TransferRequest;
import com.seapay.domain.utils.DatabaseUtils;
import com.seapay.domain.wallet.WalletRepository;
import com.seapay.domain.wallet.WalletServiceImpl;
import com.seapay.domain.wallet.entity.Wallet;
import com.seapay.domain.wallet.repository.WalletRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class WalletServiceImplTest {

    private WalletRepository walletRepository = new WalletRepositoryImpl();
    private WalletService walletService = new WalletServiceImpl(walletRepository);

    @BeforeEach
    void setUp() {
        DatabaseUtils.truncate("wallets");
    }

    @Test
    void createWalletTest() {
        String userID = UUID.randomUUID().toString();
        String walletID = UUID.randomUUID().toString();
        CreateWalletRequest createWalletRequest = CreateWalletRequest.CreateWalletRequestBuilder.aCreateWalletRequest()
                .withUserID(userID)
                .withWalletID(walletID)
                .build();
        walletService.createWallet(createWalletRequest);

        Wallet actual = walletRepository.getByUserID(userID);

        assertEquals(walletID, actual.getWalletID());
        assertEquals(userID, actual.getUserID());
        assertEquals(0, actual.getBalance());
    }

    @Test
    void getWalletIDTest() {
        String userID = UUID.randomUUID().toString();
        String walletID = UUID.randomUUID().toString();
        CreateWalletRequest createWalletRequest = CreateWalletRequest.CreateWalletRequestBuilder.aCreateWalletRequest()
                .withUserID(userID)
                .withWalletID(walletID)
                .build();
        walletService.createWallet(createWalletRequest);

        assertTrue(walletService.getWalletID(userID).isPresent());
        assertEquals(walletID, walletService.getWalletID(userID).get());
    }

    @Test
    void getWalletIDFailedTest() {
        String randomUUID = UUID.randomUUID().toString();
        assertFalse(walletService.getWalletID(randomUUID).isPresent());
        assertNull(walletService.getWalletID(randomUUID).orElse(null));
    }

    @Test
    void topUpWalletTest() {
        Long topUpAmount = 400L;
        String userID = UUID.randomUUID().toString();
        String walletID = UUID.randomUUID().toString();
        CreateWalletRequest createWalletRequest = CreateWalletRequest.CreateWalletRequestBuilder.aCreateWalletRequest()
                .withUserID(userID)
                .withWalletID(walletID)
                .build();
        walletService.createWallet(createWalletRequest);
        TopupWalletRequest topupWalletRequest = new TopupWalletRequest(walletID, topUpAmount);

        walletService.topupWallet(topupWalletRequest);

        Wallet actual = walletRepository.getByWalletID(walletID);

        assertEquals(walletID, actual.getWalletID());
        assertEquals(userID, actual.getUserID());
        assertEquals(topUpAmount, actual.getBalance());
    }

    @Test
    void transferTest() {
        Long transferAmount = 900L;
        String sourceUserID = UUID.randomUUID().toString();
        String sourceWalletID = UUID.randomUUID().toString();
        CreateWalletRequest sourceCreateWalletRequest = CreateWalletRequest.CreateWalletRequestBuilder.aCreateWalletRequest()
                .withUserID(sourceUserID)
                .withWalletID(sourceWalletID)
                .build();
        walletService.createWallet(sourceCreateWalletRequest);
        String targetUserID = UUID.randomUUID().toString();
        String targetWalletID = UUID.randomUUID().toString();
        CreateWalletRequest targetCreateWalletRequest = CreateWalletRequest.CreateWalletRequestBuilder.aCreateWalletRequest()
                .withUserID(targetUserID)
                .withWalletID(targetWalletID)
                .build();
        walletService.createWallet(targetCreateWalletRequest);
        TransferRequest transferRequest = new TransferRequest(sourceWalletID, targetWalletID, transferAmount);

        walletService.transfer(transferRequest);

        Wallet actualSourceWallet = walletRepository.getByWalletID(sourceWalletID);

        assertEquals(sourceWalletID, actualSourceWallet.getWalletID());
        assertEquals(sourceUserID, actualSourceWallet.getUserID());
        assertEquals(0 - transferAmount, actualSourceWallet.getBalance());

        Wallet actualTargetWallet = walletRepository.getByWalletID(targetWalletID);

        assertEquals(targetWalletID, actualTargetWallet.getWalletID());
        assertEquals(targetUserID, actualTargetWallet.getUserID());
        assertEquals(transferAmount, actualTargetWallet.getBalance());
    }
}