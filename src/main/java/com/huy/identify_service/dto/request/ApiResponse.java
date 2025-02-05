package com.huy.identify_service.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

// Format dữ liệu trả về từ api

@JsonInclude(JsonInclude.Include.NON_NULL)
// JsonInclude.Include.NON_NULL: chỉ include các field không null

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApiResponse<T> {
    // T is generic type là kiểu dữ liệu của data trả về từ api
    int code;
    String message;
    T data;
}
