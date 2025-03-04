package com.eshop.sonny.dto.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
public class ProductCreationRequestDto {

    private String productName;

    private int inventoryCount;

    private String productDescription;

    private String productSpecifications;

    private Double productPrice;

    private String productImages;

    private String productKeyFeatures;

}
