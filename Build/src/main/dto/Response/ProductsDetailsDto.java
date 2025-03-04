package com.eshop.sonny.dto.Response;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ProductsDetailsDto {

    private Long productId;
    private String productName;
    private String productDescription;
    private String productSpecifications;
    private Double productPrice;
    private String productImages;
    private String productKeyFeatures;
    private Integer inventoryCount;
    private Integer timesViewed;
    private Integer totalPurchased;
    private float averageRating;
    private Integer reviewsCount;

}
