package com.sparta.common.response;

public record ErrorResponse(
        String message,
        Integer status
) {}