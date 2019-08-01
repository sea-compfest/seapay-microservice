package com.seapay.api.web;

public class GenericResponse {

    private boolean success;

    private Object data;

    private String error;

    public GenericResponse(boolean success, Object data, String error) {
        this.success = success;
        this.data = data;
        this.error = error;
    }

    public static GenericResponse SuccessResponse(Object data) {
        return new GenericResponse(true, data, "");
    }

    public static GenericResponse ErrorResponse(String error) {
        return new GenericResponse(false, null, error);
    }

    public boolean isSuccess() {
        return success;
    }

    public Object getData() {
        return data;
    }

    public String getError() {
        return error;
    }
}
