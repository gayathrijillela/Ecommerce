package com.eshop.sonny.dto.Response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApiErrorResponseDto {
    private boolean success;
    private int statusCode;
    private String message;
    private LocalDateTime timestamp;

    public ApiErrorResponseDto(int statusCode, String message) {
        this.success=false;
        this.statusCode = statusCode;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }
}
