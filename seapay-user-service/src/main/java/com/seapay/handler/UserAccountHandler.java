package com.seapay.handler;

import com.seapay.api.useraccount.UserAccountService;
import com.seapay.api.useraccount.request.CreateUserAccountRequest;
import com.seapay.api.web.GenericResponse;
import com.seapay.common.marshalling.Json;
import spark.Request;
import spark.Response;

public class UserAccountHandler {

    private UserAccountService userAccountService;

    public UserAccountHandler(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    public GenericResponse createUserAccount(Request request, Response response) {
        CreateUserAccountRequest reqBody = Json
            .fromJson(request.body(), CreateUserAccountRequest.class);

        userAccountService.createAccount(reqBody);

        return GenericResponse.SuccessResponse(reqBody.getUserID());
    }
}
