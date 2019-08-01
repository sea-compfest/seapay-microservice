package com.seapay.domain.wallet;

import com.seapay.api.exception.NotFoundException;
import com.seapay.api.wallet.WalletService;
import com.seapay.api.wallet.request.CreateWalletRequest;
import com.seapay.api.wallet.request.TopupWalletRequest;
import com.seapay.api.wallet.request.TransferRequest;
import com.seapay.domain.wallet.entity.Wallet;

import java.util.Optional;

public class WalletServiceImpl implements WalletService {

    private WalletRepository walletRepository;

    public WalletServiceImpl(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Override
    public void createWallet(CreateWalletRequest request) {
        Wallet wallet = new Wallet(request.getWalletID(), request.getUserID());
        walletRepository.insert(wallet);
    }

    @Override
    public Optional<String> getWalletID(String userID) {
        return Optional.ofNullable(walletRepository.getByUserID(userID))
            .map(Wallet::getWalletID);
    }

    @Override
    public void topupWallet(TopupWalletRequest request) {
        Wallet wallet = walletRepository.getByWalletID(request.getWalletID());

        if (null == wallet) {
            throw new NotFoundException("wallet not found");
        }

        wallet.addBalance(request.getAmount());
        walletRepository.updateBalance(wallet);
    }

    @Override
    public void transfer(TransferRequest request) {
        Wallet sourceWallet = walletRepository.getByWalletID(request.getSourceID());
        if (null == sourceWallet) {
            throw new NotFoundException("sourceWallet not found");
        }

        Wallet targetWallet = walletRepository.getByWalletID(request.getTargetID());
        if (null == targetWallet) {
            throw new NotFoundException("targetWallet not found");
        }

        sourceWallet.substractBalance(request.getAmount());
        targetWallet.addBalance(request.getAmount());

        walletRepository.updateBalance(sourceWallet);
        walletRepository.updateBalance(targetWallet);
    }
}
