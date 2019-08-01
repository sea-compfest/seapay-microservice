package com.seapay.client;

import com.gojek.ApplicationConfiguration;
import com.gojek.Figaro;
import com.seapay.api.useraccount.UserAccountService;
import com.seapay.api.useraccount.request.CreateUserAccountRequest;
import com.seapay.api.web.GenericResponse;
import com.seapay.common.ClientUtils;

import java.io.IOException;
import java.util.HashSet;

import static java.util.Arrays.asList;

public class UserServiceClient implements UserAccountService {

    private static String CREATE_ACCOUNT = "/v1/sea-pay/user-account";

    private String baseUrl;

    public UserServiceClient() {
        ApplicationConfiguration configuration = Figaro.configure(new HashSet<>(asList(
            "USER_SERVICE_URL"
        )));

        this.baseUrl = configuration.getValueAsString("USER_SERVICE_URL");
    }

    @Override
    public void createAccount(CreateUserAccountRequest request) {
        String createAccountUrl = this.baseUrl + CREATE_ACCOUNT;

        try {
            GenericResponse response = ClientUtils.postRequest(createAccountUrl, request, GenericResponse.class);
            if (!response.isSuccess()) {
                throw new RuntimeException(response.getError());
            }
        } catch (IOException e) {
            throw new RuntimeException("client error");
        }
    }
}
