package com.eshop.sonny.dto.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponseDto<T> {
    private boolean success;
    private int statusCode;
    private T data;
    private String message;

    // Additional constructor for cases where no data is needed
    public ApiResponseDto(boolean success, int statusCode, String message) {
        this.success = success;
        this.statusCode = statusCode;
        this.message = message;
        this.data = null;
    }
}
