package com.huy.identify_service.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;

// Format dữ liệu trả về từ api

@JsonInclude(JsonInclude.Include.NON_NULL)
// JsonInclude.Include.NON_NULL: chỉ include các field không null

public class ApiResponse<T> {
    // T is generic type là kiểu dữ liệu của data trả về từ api
    private int code;
    private String message;
    private T data;

    public ApiResponse(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
