package com.eshop.sonny.dto.Request;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class ReviewRequestDto {

    @NotNull(message = "Product ID is required")
    private Long productId;

    @NotBlank(message = "Buyer name is required")
    private String buyerUsername;

    @Size(max = 1000, message = "Description cannot exceed 1000 characters")
    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "Rating is required")
    @Min(1) @Max(5)
    private Integer rating;
}
