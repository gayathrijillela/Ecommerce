package com.eshop.sonny.service;

import com.eshop.sonny.dto.Request.SubCategoryRequestDto;
import com.eshop.sonny.dto.Response.SubCategoryResponseDto;

public interface SubCategoriesService {

    boolean addSubCategory(String categoryName, SubCategoryRequestDto subCategory);

    boolean deleteSubCategory(String categoryName, String subCategoryName);

    boolean updateSubCategory(String categoryName, SubCategoryRequestDto subCategory);

    SubCategoryResponseDto getSubCategory(String categoryName, String subCategoryName);

}
