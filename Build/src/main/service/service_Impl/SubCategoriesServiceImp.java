package com.eshop.sonny.service.service_Impl;

import com.eshop.sonny.dto.Request.SubCategoryRequestDto;
import com.eshop.sonny.dto.Response.SubCategoryResponseDto;
import com.eshop.sonny.mapper.mappersClasses.SubCategoryMapper;
import com.eshop.sonny.model.Category;
import com.eshop.sonny.model.SubCategory;
import com.eshop.sonny.repository.CategoryRepository;
import com.eshop.sonny.repository.SubCategoryRepository;
import com.eshop.sonny.service.SubCategoriesService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class SubCategoriesServiceImp implements SubCategoriesService {

    private final SubCategoryRepository subCategoryRepository;
    private final SubCategoryMapper subCategoryMapper;
    private final CategoryRepository categoryRepository;


    @Override
    public boolean addSubCategory(String categoryName, SubCategoryRequestDto subCategoryDto) {
        Category category = categoryRepository.findByCategoryName(categoryName);
        if(category == null){
            throw new RuntimeException("Category not found");
        }

        if(subCategoryRepository.existsBySubcategoryName(subCategoryDto.getSubCategoryName())) {
            throw new RuntimeException("SubCategory already exists");
        }

        SubCategory subCategory = SubCategoryMapper.SubCategoryRequestDtoToSubCategory(subCategoryDto);
        subCategory.setCategoryId(category);
        subCategoryRepository.save(subCategory);

        return true;
    }

    @Override
    public boolean deleteSubCategory(String categoryName, String subCategoryName) {
        Category category = categoryRepository.findByCategoryName(categoryName);
        if(category == null){
            throw new RuntimeException("Category not found");
        }

        if(!subCategoryRepository.existsBySubcategoryName(subCategoryName)) {
            throw new RuntimeException("SubCategory did not exists");
        }

        SubCategory subCategory = subCategoryRepository.findBySubcategoryName(subCategoryName);
        subCategoryRepository.delete(subCategory);
        return true;
    }

    @Override
    public boolean updateSubCategory(String categoryName, SubCategoryRequestDto subCategory) {
        Category category = categoryRepository.findByCategoryName(categoryName);
        if(category == null){
            throw new RuntimeException("Category not found");
        }

        if(!subCategoryRepository.existsBySubcategoryName(subCategory.getSubCategoryName())) {
            throw new RuntimeException("SubCategory did not exists");
        }

        SubCategory subCategoryExists = subCategoryRepository.findBySubcategoryName(subCategory.getSubCategoryName());
        subCategoryExists.setSubcategoryName(subCategory.getSubCategoryName());
        subCategoryExists.setSubcategory_url(subCategory.getSubCategoryUrl());
        subCategoryExists.setSubcategory_banner(subCategory.getSubCategoryBanner());
        subCategoryRepository.save(subCategoryExists);

        return true;
    }

    @Override
    public SubCategoryResponseDto getSubCategory(String categoryName, String subCategoryName) {
        Category category = categoryRepository.findByCategoryName(categoryName);
        if(category == null){
            throw new RuntimeException("Category not found");
        }
        if(!subCategoryRepository.existsBySubcategoryName(subCategoryName)) {
            throw new RuntimeException("SubCategory dose not exists");
        }

        SubCategory subCategory = subCategoryRepository.findBySubcategoryName(subCategoryName);
        return SubCategoryMapper.SubCategoryToSubCategoryResponseDto(subCategory);

    }


}
