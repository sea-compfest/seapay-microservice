package com.seapay.server;

import com.seapay.common.marshalling.Json;
import spark.ResponseTransformer;

public class JsonTransformer implements ResponseTransformer {

    @Override
    public String render(Object model) throws Exception {

        if (model != null && model.getClass() == String.class) {
            return (String) model;
        }

        return Json.toJson(model);
    }
}

