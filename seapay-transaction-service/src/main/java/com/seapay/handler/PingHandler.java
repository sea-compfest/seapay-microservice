package com.seapay.handler;

import spark.Request;
import spark.Response;

public class PingHandler {

    public static Object ping(Request request, Response response) {
        response.body("pong");
        return null;
    }
}
