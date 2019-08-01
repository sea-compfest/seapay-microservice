package com.seapay.handler;

import com.seapay.api.transaction.TransactionService;
import com.seapay.api.transaction.request.CreateTransactionRequest;
import com.seapay.api.useraccount.UserAccountService;
import com.seapay.api.useraccount.request.CreateUserAccountRequest;
import com.seapay.api.web.GenericResponse;
import com.seapay.common.marshalling.Json;
import spark.Request;
import spark.Response;

public class TransactionHandler {

    private TransactionService transactionService;

    public TransactionHandler(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    public GenericResponse createTransaction(Request request, Response response) {
        CreateTransactionRequest reqBody = Json
            .fromJson(request.body(), CreateTransactionRequest.class);

        transactionService.createTransaction(reqBody);

        return GenericResponse.SuccessResponse(reqBody.getTransactionID());
    }
}
