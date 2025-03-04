package com.eshop.sonny.dto.Response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ReviewResponseDto {
    private Long id;
    private String buyerUsername;
    private Long productId;
    private String description;
    private Integer rating;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}