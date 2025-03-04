package com.eshop.sonny.dto.Response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data

public class AllCategoryResponse {
    private List<CategoryResponseDto> categoryResponseDto;
}
