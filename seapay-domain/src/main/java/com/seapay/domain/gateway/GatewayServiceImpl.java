package com.seapay.domain.gateway;

import com.seapay.api.exception.NotFoundException;
import com.seapay.api.gateway.GatewayService;
import com.seapay.api.gateway.request.PayRequest;
import com.seapay.api.gateway.request.RegisterCustomerRequest;
import com.seapay.api.gateway.request.RegisterMerchantRequest;
import com.seapay.api.gateway.request.TopupRequest;
import com.seapay.api.gateway.response.PayResponse;
import com.seapay.api.gateway.response.RegisterCustomerResponse;
import com.seapay.api.gateway.response.RegisterMerchantResponse;
import com.seapay.api.gateway.response.TopupResponse;
import com.seapay.api.transaction.TransactionService;
import com.seapay.api.transaction.request.CreateTransactionRequest;
import com.seapay.api.useraccount.UserAccountService;
import com.seapay.api.useraccount.request.CreateUserAccountRequest;
import com.seapay.api.wallet.WalletService;
import com.seapay.api.wallet.request.CreateWalletRequest;
import com.seapay.api.wallet.request.TopupWalletRequest;
import com.seapay.api.wallet.request.TransferRequest;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.seapay.domain.constant.SeaPayConstant.*;

public class GatewayServiceImpl implements GatewayService {

    private UserAccountService userAccountService;

    private WalletService walletService;

    private TransactionService transactionService;

    public GatewayServiceImpl(UserAccountService userAccountService, WalletService walletService, TransactionService transactionService) {
        this.userAccountService = userAccountService;
        this.walletService = walletService;
        this.transactionService = transactionService;
    }

    @Override
    public RegisterCustomerResponse registerCustomer(RegisterCustomerRequest request) {
        String userID = UUID.randomUUID().toString();
        registerAccount(userID, request.getName(), request.getEmail(), request.getPhonenumber(), CUSTOMER_TYPE);

        return new RegisterCustomerResponse(userID, request.getName(), request.getEmail(), request.getPhonenumber());
    }

    @Override
    public RegisterMerchantResponse registerMerchant(RegisterMerchantRequest request) {
        String userID = UUID.randomUUID().toString();
        registerAccount(userID, request.getName(), request.getEmail(), "", MERCHANT_TYPE);
        return new RegisterMerchantResponse(userID, request.getName(), request.getEmail());
    }

    @Override
    public TopupResponse topup(TopupRequest request) {

        String walletId = walletService
            .getWalletID(request.getUserID())
            .orElseThrow(() -> new NotFoundException("wallet not found"));

        String transactionID = UUID.randomUUID().toString();
        CreateTransactionRequest createTransactionRequest = CreateTransactionRequest.CreateTransactionRequestBuilder.aCreateTransactionRequest()
            .withCreditedWallet(walletId)
            .withDebitedWallet(SAMPLE_BANK_WALLET_ID)
            .withAmount(request.getAmount())
            .withDescription(request.getDescription())
            .withReferenceID(request.getReferenceID())
            .withTransactionDate(LocalDateTime.now())
            .withTransactionID(transactionID)
            .withTransactionType(TOPUP_TRANSACTION)
            .build();

        transactionService.createTransaction(createTransactionRequest);

        TopupWalletRequest topupWalletRequest = new TopupWalletRequest(walletId, request.getAmount());
        walletService.topupWallet(topupWalletRequest);

        return new TopupResponse(transactionID, request.getUserID(), request.getAmount(), request.getReferenceID(), request.getDescription());
    }

    @Override
    public PayResponse pay(PayRequest request) {

        String userWallet = walletService
            .getWalletID(request.getUserID())
            .orElseThrow(() -> new NotFoundException("user wallet not found"));

        String merchantWallet = walletService
            .getWalletID(request.getMerchantID())
            .orElseThrow(() -> new NotFoundException("merchant wallet not found"));

        String transactionID = UUID.randomUUID().toString();
        CreateTransactionRequest createTransactionRequest = CreateTransactionRequest.CreateTransactionRequestBuilder.aCreateTransactionRequest()
            .withCreditedWallet(merchantWallet)
            .withDebitedWallet(userWallet)
            .withAmount(request.getAmount())
            .withDescription(request.getDescription())
            .withReferenceID(request.getReferenceID())
            .withTransactionDate(LocalDateTime.now())
            .withTransactionID(transactionID)
            .withTransactionType(PAY_TRANSACTION)
            .build();

        transactionService.createTransaction(createTransactionRequest);

        TransferRequest transferRequest = new TransferRequest(userWallet, merchantWallet, request.getAmount());
        walletService.transfer(transferRequest);

        return new PayResponse(transactionID, request.getUserID(), request.getMerchantID(), request.getAmount(), request.getReferenceID(), request.getDescription());
    }

    private String registerAccount(String userID, String name, String email, String phonenumber, int userType) {

        CreateUserAccountRequest userAccountRequest = CreateUserAccountRequest.CreateUserAccountRequestBuilder
            .aCreateUserAccountRequest()
            .withUserID(userID)
            .withEmail(email)
            .withName(name)
            .withPhonenumber(phonenumber)
            .withUserType(userType)
            .build();

        userAccountService.createAccount(userAccountRequest);

        String walletID = UUID.randomUUID().toString();
        CreateWalletRequest walletRequest = CreateWalletRequest.CreateWalletRequestBuilder.aCreateWalletRequest()
            .withUserID(userID)
            .withWalletID(walletID)
            .build();

        walletService.createWallet(walletRequest);

        return userID;
    }
}
