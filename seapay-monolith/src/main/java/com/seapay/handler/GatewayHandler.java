package com.seapay.handler;

import com.seapay.api.gateway.GatewayService;
import com.seapay.api.gateway.request.PayRequest;
import com.seapay.api.gateway.request.RegisterCustomerRequest;
import com.seapay.api.gateway.request.RegisterMerchantRequest;
import com.seapay.api.gateway.request.TopupRequest;
import com.seapay.api.web.GenericResponse;
import com.seapay.common.marshalling.Json;
import spark.Request;
import spark.Response;

public class GatewayHandler {

    private GatewayService gatewayService;

    public GatewayHandler(GatewayService gatewayService) {
        this.gatewayService = gatewayService;
    }

    public GenericResponse registerCustomer(Request request, Response response) {
        RegisterCustomerRequest reqBody = Json
            .fromJson(request.body(), RegisterCustomerRequest.class);

        return GenericResponse.SuccessResponse(gatewayService.registerCustomer(reqBody));
    }

    public GenericResponse registerMerchant(Request request, Response response) {
        RegisterMerchantRequest reqBody = Json
            .fromJson(request.body(), RegisterMerchantRequest.class);

        return GenericResponse.SuccessResponse(gatewayService.registerMerchant(reqBody));
    }

    public GenericResponse topup(Request request, Response response) {
        TopupRequest reqBody = Json
            .fromJson(request.body(), TopupRequest.class);

        return GenericResponse.SuccessResponse(gatewayService.topup(reqBody));
    }

    public GenericResponse pay(Request request, Response response) {
        PayRequest reqBody = Json
            .fromJson(request.body(), PayRequest.class);

        return GenericResponse.SuccessResponse(gatewayService.pay(reqBody));
    }
}
