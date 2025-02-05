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
        return ResponseEntity.badRequest().body(ApiResponse.builder()
                .code(ErrorCode.UNCATEGORIZED_EXCEPTION.getCode())
                .message(ErrorCode.UNCATEGORIZED_EXCEPTION.getMessage())
                .build()
        );
    }

    //    handle app exception and return error code and message
    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiResponse> handleAppException(AppException e) {
        ErrorCode errorCode = e.getErrorCode();
        return ResponseEntity.badRequest().body(ApiResponse.builder()
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .build()
        );
    }

    //    handle validation exception and return error code and message
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
//        return mgs and status code
        return ResponseEntity.badRequest().body(ApiResponse.builder()
                .code(StatusCode.BAD_REQUEST)
                .message(e.getBindingResult().getFieldError().getDefaultMessage())
                .build()
        );
    }
}
