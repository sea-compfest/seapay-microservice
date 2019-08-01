package com.seapay.handler;

import com.seapay.api.exception.NotFoundException;
import com.seapay.api.wallet.WalletService;
import com.seapay.api.wallet.request.CreateWalletRequest;
import com.seapay.api.wallet.request.TopupWalletRequest;
import com.seapay.api.wallet.request.TransferRequest;
import com.seapay.api.web.GenericResponse;
import com.seapay.common.marshalling.Json;
import spark.Request;
import spark.Response;

import java.util.Optional;

public class WalletHandler {

    private WalletService walletService;

    public WalletHandler(WalletService walletService) {
        this.walletService = walletService;
    }

    public GenericResponse createWallet(Request request, Response response) {
        CreateWalletRequest reqBody = Json
            .fromJson(request.body(), CreateWalletRequest.class);

        walletService.createWallet(reqBody);

        return GenericResponse.SuccessResponse(reqBody.getWalletID());
    }

    public GenericResponse transfer(Request request, Response response) {
        TransferRequest reqBody = Json
            .fromJson(request.body(), TransferRequest.class);

        walletService.transfer(reqBody);

        return GenericResponse.SuccessResponse(reqBody.getTargetID());
    }

    public GenericResponse topup(Request request, Response response) {
        TopupWalletRequest reqBody = Json
            .fromJson(request.body(), TopupWalletRequest.class);

        walletService.topupWallet(reqBody);

        return GenericResponse.SuccessResponse(reqBody.getWalletID());
    }

    public GenericResponse getWalletID(Request request, Response response) {
        String userID = request.queryParams("user-id");

        String walletID = walletService.getWalletID(userID)
            .orElseThrow(() -> new NotFoundException("user_id not found"));

        return GenericResponse.SuccessResponse(walletID);
    }
}
