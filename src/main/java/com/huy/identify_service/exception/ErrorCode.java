package com.huy.identify_service.exception;

// ErrorCode là enum chứa mã lỗi và thông báo lỗi để service trả về cho client
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(0, "Uncategorized exception"),
    USERNAME_EXISTED(1001, "Username existed"),
    USER_NOT_FOUND(1002, "User not found"),
    USER_NOT_EXISTED(1003, "User not existed"),
    UNAUTHENTICATED(1004, "Unauthenticated"),
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
