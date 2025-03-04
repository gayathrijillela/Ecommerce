package com.eshop.sonny.dto.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryRequestDto {

    private String categoryName;
    private String categoryUrl;
    private String categoryBanner;

}
