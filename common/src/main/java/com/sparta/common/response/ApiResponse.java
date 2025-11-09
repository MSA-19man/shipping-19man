package com.sparta.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ApiResponse<T>(
        boolean success,
        String message,
        T data,
        Long timestamp
) {
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(true, null, data, System.currentTimeMillis());
    }
    public static <T> ApiResponse<T> success(T data, String message) {
        return new ApiResponse<>(true, message, data, System.currentTimeMillis());
    }
    public static <T> ApiResponse<T> success(String message) {
        return new ApiResponse<>(true, message, null, System.currentTimeMillis());
    }
    public static <T> ApiResponse<T> error(T errorData) {
        return new ApiResponse<>(false, null, errorData, System.currentTimeMillis());
    }
}

