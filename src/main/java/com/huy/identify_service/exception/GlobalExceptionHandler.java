package com.huy.identify_service.exception;

import com.huy.identify_service.constants.StatusCode;
import com.huy.identify_service.dto.request.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    //    handle all exception and return error code and message
    @ExceptionHandler(value = Exception.class)
    ResponseEntity<ApiResponse> handleRuntimeException(RuntimeException e) {
        return ResponseEntity.badRequest().body(new ApiResponse(ErrorCode.UNCATEGORIZED_EXCEPTION.getCode(), ErrorCode.UNCATEGORIZED_EXCEPTION.getMessage(), null));
    }

    //    handle app exception and return error code and message
    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiResponse> handleAppException(AppException e) {
        ErrorCode errorCode = e.getErrorCode();
        return ResponseEntity.badRequest().body(new ApiResponse(errorCode.getCode(), errorCode.getMessage(), null));
    }

    //    handle validation exception and return error code and message
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
//        return mgs and status code
        return ResponseEntity.badRequest().body(new ApiResponse(StatusCode.BAD_REQUEST, e.getBindingResult().getFieldError().getDefaultMessage(), null));
    }
}
