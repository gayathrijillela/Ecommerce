package com.eshop.sonny.mapper.mappersClasses;

import com.eshop.sonny.dto.Request.SubCategoryRequestDto;
import com.eshop.sonny.dto.Response.ProductsResponseDto;
import com.eshop.sonny.dto.Response.SubCategoryResponseDto;
import com.eshop.sonny.model.Product;
import com.eshop.sonny.model.SubCategory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component

public class SubCategoryMapper {

    public static SubCategory SubCategoryRequestDtoToSubCategory(SubCategoryRequestDto subCategoryRequestDto) {
        SubCategory subCategory = new SubCategory();
        subCategory.setSubcategoryName(subCategoryRequestDto.getSubCategoryName());
        subCategory.setSubcategory_url(subCategoryRequestDto.getSubCategoryUrl());
        subCategory.setSubcategory_banner(subCategoryRequestDto.getSubCategoryBanner());
        return subCategory;
    }

    public static SubCategoryResponseDto SubCategoryToSubCategoryResponseDto(SubCategory subCategory) {
        SubCategoryResponseDto subCategoryResponseDto = new SubCategoryResponseDto();
        subCategoryResponseDto.setSubCategoryName(subCategory.getSubcategoryName());
        subCategoryResponseDto.setProductsResponseDtoList(SubCategoryMapper.productsDtoList(
                subCategory.getProducts()
        ));
        return subCategoryResponseDto;
    }

    private static List<ProductsResponseDto> productsDtoList(List<Product> products) {
        if (products != null) {
            return products.stream().map(product -> new ProductsResponseDto(
                    product.getId(),
                    product.getProductName(),
                    product.getProductPrice()
            )).toList();
        }
        return null;
    }

}
