package com.seapay.common;

import com.gojek.ApplicationConfiguration;
import com.gojek.Figaro;
import com.seapay.common.marshalling.Json;
import okhttp3.*;
import okhttp3.logging.HttpLoggingInterceptor;

import java.io.IOException;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;

import static java.util.Arrays.asList;

public class ClientUtils {

    private static OkHttpClient client = buildClient();

    private static OkHttpClient buildClient() {
        ApplicationConfiguration configuration = Figaro.configure(new HashSet<>(asList(
            "CONNECTIONS_TIMEOUT_IN_MS",
            "READ_TIMEOUT_IN_MS"
        )));

        return new OkHttpClient.Builder() //
            .addInterceptor(new HttpLoggingInterceptor())
            .connectTimeout(configuration.getValueAsLong("CONNECTIONS_TIMEOUT_IN_MS"), TimeUnit.MILLISECONDS)
            .readTimeout(configuration.getValueAsLong("READ_TIMEOUT_IN_MS"), TimeUnit.MILLISECONDS)
            .build();
    }

    public static <T> T postRequest(String url, Object body, Class<T> responseClass) throws IOException {

        String json = Json.toJson(body);

        RequestBody postBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);

        Request request = new Request.Builder()
            .url(url)
            .post(postBody)
            .build();

        Call call = client.newCall(request);
        Response response = call.execute();

        return fromResponse(response, responseClass);
    }

    public static <T> T getRequest(String url, Class<T> responseClass) throws IOException {
        Request request = new Request.Builder()
            .url(url)
            .get()
            .build();

        Call call = client.newCall(request);
        Response response = call.execute();

        return fromResponse(response, responseClass);
    }

    private static <T> T fromResponse(Response response, Class<T> responseClass) throws IOException {
        String responseString = response.body().string();
        return Json.fromJson(responseString, responseClass);
    }
}
