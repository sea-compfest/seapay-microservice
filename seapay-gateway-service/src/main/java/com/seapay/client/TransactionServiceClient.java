package com.seapay.client;

import com.gojek.ApplicationConfiguration;
import com.gojek.Figaro;
import com.seapay.api.transaction.TransactionService;
import com.seapay.api.transaction.request.CreateTransactionRequest;
import com.seapay.api.web.GenericResponse;
import com.seapay.common.ClientUtils;

import java.io.IOException;
import java.util.HashSet;

import static java.util.Arrays.asList;

public class TransactionServiceClient implements TransactionService {

    private static String CREATE_TRANSACTION = "/v1/sea-pay/transaction";

    private String baseUrl;

    public TransactionServiceClient() {
        ApplicationConfiguration configuration = Figaro.configure(new HashSet<>(asList(
            "TRANSACTION_SERVICE_URL"
        )));

        this.baseUrl = configuration.getValueAsString("TRANSACTION_SERVICE_URL");
    }

    @Override
    public void createTransaction(CreateTransactionRequest request) {
        String createTransactionUrl = this.baseUrl + CREATE_TRANSACTION;

        try {
            GenericResponse response = ClientUtils.postRequest(createTransactionUrl, request, GenericResponse.class);
            if (!response.isSuccess()) {
                throw new RuntimeException(response.getError());
            }
        } catch (IOException e) {
            throw new RuntimeException("client error");
        }

    }
}
