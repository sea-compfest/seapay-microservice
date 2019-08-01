package com.seapay.client;

import com.gojek.ApplicationConfiguration;
import com.gojek.Figaro;
import com.seapay.api.wallet.WalletService;
import com.seapay.api.wallet.request.CreateWalletRequest;
import com.seapay.api.wallet.request.TopupWalletRequest;
import com.seapay.api.wallet.request.TransferRequest;
import com.seapay.api.web.GenericResponse;
import com.seapay.common.ClientUtils;

import java.io.IOException;
import java.util.HashSet;
import java.util.Optional;

import static java.util.Arrays.asList;

public class WalletServiceClient implements WalletService {
    private static String CREATE_WALLET = "/v1/sea-pay/wallet";

    private static String GET_WALLET = "/v1/sea-pay/wallet/get-wallet-by-user?user-id=";

    private static String TOPUP_WALLET = "/v1/sea-pay/wallet/topup";

    private static String TRANSFER_WALLET = "/v1/sea-pay/wallet/transfer";

    private String baseUrl;

    public WalletServiceClient() {
        ApplicationConfiguration configuration = Figaro.configure(new HashSet<>(asList(
            "WALLET_SERVICE_URL"
        )));

        this.baseUrl = configuration.getValueAsString("WALLET_SERVICE_URL");
    }

    @Override
    public void createWallet(CreateWalletRequest request) {
        String createWalletUrl = this.baseUrl + CREATE_WALLET;

        try {
            GenericResponse response = ClientUtils.postRequest(createWalletUrl, request, GenericResponse.class);
            if (!response.isSuccess()) {
                throw new RuntimeException(response.getError());
            }
        } catch (IOException e) {
            throw new RuntimeException("client error");
        }
    }

    @Override
    public Optional<String> getWalletID(String userID) {
        String getWalletUrl = this.baseUrl + GET_WALLET + userID;

        try {
            GenericResponse response = ClientUtils.getRequest(getWalletUrl, GenericResponse.class);
            if (!response.isSuccess()) {
                return Optional.empty();
            }

            return Optional.of((String) response.getData());
        } catch (IOException e) {
            return Optional.empty();
        }
    }

    @Override
    public void topupWallet(TopupWalletRequest request) {
        String topupUrl = this.baseUrl + TOPUP_WALLET;

        try {
            GenericResponse response = ClientUtils.postRequest(topupUrl, request, GenericResponse.class);
            if (!response.isSuccess()) {
                throw new RuntimeException(response.getError());
            }
        } catch (IOException e) {
            throw new RuntimeException("client error");
        }
    }

    @Override
    public void transfer(TransferRequest request) {
        String transferUrl = this.baseUrl + TRANSFER_WALLET;

        try {
            GenericResponse response = ClientUtils.postRequest(transferUrl, request, GenericResponse.class);
            if (!response.isSuccess()) {
                throw new RuntimeException(response.getError());
            }
        } catch (IOException e) {
            throw new RuntimeException("client error");
        }
    }
}
