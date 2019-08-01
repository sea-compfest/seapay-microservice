package com.seapay.api.gateway;

import com.seapay.api.gateway.request.PayRequest;
import com.seapay.api.gateway.request.RegisterCustomerRequest;
import com.seapay.api.gateway.request.RegisterMerchantRequest;
import com.seapay.api.gateway.request.TopupRequest;
import com.seapay.api.gateway.response.PayResponse;
import com.seapay.api.gateway.response.RegisterCustomerResponse;
import com.seapay.api.gateway.response.RegisterMerchantResponse;
import com.seapay.api.gateway.response.TopupResponse;

public interface GatewayService {
    RegisterCustomerResponse registerCustomer(RegisterCustomerRequest request);
    RegisterMerchantResponse registerMerchant(RegisterMerchantRequest request);
    TopupResponse topup(TopupRequest request);
    PayResponse pay(PayRequest request);
}
