package com.huy.identify_service.exception;

// ErrorCode là enum chứa mã lỗi và thông báo lỗi để service trả về cho client
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(0, "Uncategorized exception"),
    USERNAME_EXISTED(1001, "Username existed"),
    ;

    private int code;
    private String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
