package com.seapay.server;

import com.gojek.ApplicationConfiguration;
import com.gojek.Figaro;
import com.google.gson.JsonSyntaxException;
import com.seapay.api.exception.NotFoundException;
import com.seapay.api.transaction.TransactionService;
import com.seapay.api.useraccount.UserAccountService;
import com.seapay.api.web.GenericResponse;
import com.seapay.common.marshalling.Json;
import com.seapay.handler.PingHandler;
import com.seapay.handler.TransactionHandler;
import org.eclipse.jetty.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;

import static java.util.Arrays.asList;
import static spark.Spark.*;

public class Router {

    private static final Logger logger = LoggerFactory.getLogger(Router.class);

    private TransactionHandler transactionHandler;

    private ApplicationConfiguration configuration;

    public Router(TransactionService transactionService) {
        this.transactionHandler = new TransactionHandler(transactionService);
        this.configuration = Figaro.configure(new HashSet<>(asList(
            "PORT"
        )));
    }

    public void configure() {
        int apiPort = configuration.getValueAsInt("PORT");
        port(apiPort);

        get("/ping", PingHandler::ping);

        path("/v1/sea-pay/transaction", () -> {
            post("", transactionHandler::createTransaction, new JsonTransformer());

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
