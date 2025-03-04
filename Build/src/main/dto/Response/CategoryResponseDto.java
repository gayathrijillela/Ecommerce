package com.eshop.sonny.dto.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryResponseDto {

    private String categoryName;
    private String categoryUrl;
    private String categoryBanner;
    // should we put the hole subcategories or just the names
    private List<String> subCategoriesName;

}
