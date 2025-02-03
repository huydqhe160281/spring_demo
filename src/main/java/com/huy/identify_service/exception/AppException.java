package com.huy.identify_service.exception;


// AppException kế thừa từ RuntimeException,
// xử lý những exception xảy ra trong ứng dụng(thường là sử dụng trong service)
public class AppException extends RuntimeException {
    public AppException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    private ErrorCode errorCode;

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
