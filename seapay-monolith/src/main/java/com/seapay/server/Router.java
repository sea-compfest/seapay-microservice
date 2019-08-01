package com.seapay.server;

import com.gojek.ApplicationConfiguration;
import com.gojek.Figaro;
import com.google.gson.JsonSyntaxException;
import com.seapay.api.exception.NotFoundException;
import com.seapay.api.gateway.GatewayService;
import com.seapay.api.web.GenericResponse;
import com.seapay.common.marshalling.Json;
import com.seapay.handler.GatewayHandler;
import com.seapay.handler.PingHandler;
import org.eclipse.jetty.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;

import static java.util.Arrays.asList;
import static spark.Spark.*;

public class Router {

    private static final Logger logger = LoggerFactory.getLogger(Router.class);

    private GatewayHandler gatewayHandler;

    private ApplicationConfiguration configuration;

    public Router(GatewayService gatewayService) {
        this.gatewayHandler = new GatewayHandler(gatewayService);
        this.configuration = Figaro.configure(new HashSet<>(asList(
            "PORT"
        )));
    }

    public void configure() {
        int apiPort = configuration.getValueAsInt("PORT");
        port(apiPort);

        get("/ping", PingHandler::ping);

        path("/v1/sea-pay", () -> {
            post("/customer", gatewayHandler::registerCustomer, new JsonTransformer());
            post("/merchant", gatewayHandler::registerMerchant, new JsonTransformer());
            post("/topup", gatewayHandler::topup, new JsonTransformer());
            post("/pay", gatewayHandler::pay, new JsonTransformer());
        });

        after((request, response) -> response.type("application/json"));

        exception(NotFoundException.class, (exception, request, response) -> {
            response.status(HttpStatus.NOT_FOUND_404);
            response.body(Json.toJson(GenericResponse.ErrorResponse(exception.getMessage())));
        });

        exception(JsonSyntaxException.class, (exception, request, response) -> {
            response.status(HttpStatus.BAD_REQUEST_400);
            logger.error("JsonSyntaxException error", exception);
            response.body(Json.toJson(GenericResponse.ErrorResponse(HttpStatus.getMessage(HttpStatus.BAD_REQUEST_400))));
        });

        exception(Exception.class, (exception, request, response) -> {
            logger.error("unknown error", exception);
            response.status(HttpStatus.INTERNAL_SERVER_ERROR_500);
            response.body(Json.toJson(GenericResponse.ErrorResponse(HttpStatus.getMessage(HttpStatus.INTERNAL_SERVER_ERROR_500))));
        });
    }
}
