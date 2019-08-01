package com.seapay.api.wallet;

import com.seapay.api.wallet.request.CreateWalletRequest;
import com.seapay.api.wallet.request.TopupWalletRequest;
import com.seapay.api.wallet.request.TransferRequest;

import java.util.Optional;

public interface WalletService {
    void createWallet(CreateWalletRequest request);

    Optional<String> getWalletID(String userID);

    void topupWallet(TopupWalletRequest request);

    void transfer(TransferRequest request);
}
