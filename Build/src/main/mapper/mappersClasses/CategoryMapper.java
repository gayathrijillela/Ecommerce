package com.eshop.sonny.mapper.mappersClasses;

import com.eshop.sonny.dto.Request.CategoryRequestDto;
import com.eshop.sonny.dto.Response.CategoryResponseDto;
import com.eshop.sonny.model.Category;
import com.eshop.sonny.model.SubCategory;
import org.springframework.stereotype.Component;

@Component


public class CategoryMapper {

    public static Category CategoryResponseDtoTocategory(CategoryRequestDto categoryRequestDto) {
        Category category = new Category();
        category.setCategoryName(categoryRequestDto.getCategoryName());
        category.setCategory_url(categoryRequestDto.getCategoryUrl());
        category.setCategory_banner(categoryRequestDto.getCategoryBanner());
        return category;
    }


    public static CategoryResponseDto categoryToCategoryResponseDto(Category category) {
        CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
        categoryResponseDto.setCategoryName(category.getCategoryName());
        categoryResponseDto.setCategoryUrl(category.getCategory_url());
        categoryResponseDto.setCategoryBanner(category.getCategory_banner());
        categoryResponseDto.setSubCategoriesName(
                category.getSubCategories().stream()
                        .map(SubCategory::getSubcategoryName)
                        .toList()
        );
        return categoryResponseDto;
    }



}
