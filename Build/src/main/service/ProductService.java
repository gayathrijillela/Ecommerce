package com.eshop.sonny.service;

import com.eshop.sonny.QuerySpecification.Spacifications.ProductSpecification;
import com.eshop.sonny.dto.Request.ProductCreationRequestDto;
import com.eshop.sonny.dto.Response.ProductsDetailsDto;

import java.util.List;

public interface ProductService {

    boolean saveProduct(
            String sellerBusinessName,
            String categoryName,
            String subCategoryName,
            ProductCreationRequestDto product);

    boolean deleteProduct(String sellerBusinessName,Long productId);

    boolean updateProduct(String sellerBusinessName, Long productId, ProductCreationRequestDto product);

    ProductsDetailsDto getProductDetails(Long productId);

    // we need to add dynamic quaries to get products

    List<ProductsDetailsDto> searchProducts(ProductSpecification productSpecification); ;


    }
