package com.eshop.sonny.mapper.mappersClasses;

import com.eshop.sonny.dto.Request.ProductCreationRequestDto;
import com.eshop.sonny.dto.Response.ProductsDetailsDto;
import com.eshop.sonny.dto.Response.ProductsResponseDto;
import com.eshop.sonny.model.Product;
import org.springframework.stereotype.Component;

@Component

public class ProductMapper {

    public static Product ProductCreationDtoToProduct(ProductCreationRequestDto productDto) {

        Product product = new Product();

        product.setProductName(productDto.getProductName());
        product.setProductPrice(productDto.getProductPrice());
        product.setInventoryCount(productDto.getInventoryCount());
        product.setProductDescription(productDto.getProductDescription());
        product.setProductSpecifications(productDto.getProductSpecifications());
        product.setProductKeyFeatures(productDto.getProductKeyFeatures());
        product.setProductImages(productDto.getProductImages());

        return product;
    }

    public static ProductsDetailsDto ProductToProductDetailsDto(Product product){

        ProductsDetailsDto productsDetailsDto = new ProductsDetailsDto();

        productsDetailsDto.setProductId(product.getId());
        productsDetailsDto.setProductName(product.getProductName());
        productsDetailsDto.setProductPrice(product.getProductPrice());
        productsDetailsDto.setInventoryCount(product.getInventoryCount());
        productsDetailsDto.setProductDescription(product.getProductDescription());
        productsDetailsDto.setProductSpecifications(product.getProductSpecifications());
        productsDetailsDto.setProductKeyFeatures(product.getProductKeyFeatures());
        productsDetailsDto.setProductImages(product.getProductImages());
        productsDetailsDto.setTimesViewed(product.getTimesViewed());
        productsDetailsDto.setTotalPurchased(product.getTotalPurchased());
        productsDetailsDto.setAverageRating(product.getAverageRating());
        productsDetailsDto.setReviewsCount(product.getReviewsCount());

        return productsDetailsDto;

    }

    public static ProductsResponseDto productToProductResponseDto(Product product){
        ProductsResponseDto productsResponseDto=new ProductsResponseDto();
        productsResponseDto.setId(product.getId());
        productsResponseDto.setProductName(product.getProductName());
        productsResponseDto.setProductPrice(product.getProductPrice());
        return productsResponseDto;
    }




}
