package com.seapay.common.marshalling;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonNull;

import java.time.LocalDateTime;

public class Json {

    private static final Gson gson =  new GsonBuilder()
        .serializeNulls()
        .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer())
        .create();

    public static <T> T fromJson(String json, Class<T> classOfT) {
        return gson.fromJson(json, classOfT);
    }

    public static String toJson(Object src) {
        if (src == null) {
            return toJson(JsonNull.INSTANCE);
        }
        return gson.toJson(src, src.getClass());
    }
}
