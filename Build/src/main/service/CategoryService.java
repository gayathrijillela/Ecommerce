package com.eshop.sonny.service;

import com.eshop.sonny.dto.Request.CategoryRequestDto;
import com.eshop.sonny.dto.Request.SubCategoryRequestDto;
import com.eshop.sonny.dto.Response.AllCategoryResponse;
import com.eshop.sonny.dto.Response.CategoryResponseDto;
import com.eshop.sonny.dto.Response.ProductsResponseDto;
import com.eshop.sonny.model.Category;

import java.util.List;

public interface CategoryService {

    boolean save(CategoryRequestDto category);

    boolean update(CategoryRequestDto category);

    boolean delete(String categoryName);

    CategoryResponseDto getSpacificCategory(String categoryName);

    AllCategoryResponse getAllCategories();

    boolean addProductToRecommendation(String categoryName, long id);

    boolean deleteProductFromRecommendation(String categoryName, Long id);

    List<ProductsResponseDto> getProductFromSpecialCategory(String categoryName, Long id);


}
