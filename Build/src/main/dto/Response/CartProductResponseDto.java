package com.eshop.sonny.dto.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CartProductResponseDto {

    private String image;
    private String productName;
    private Double price;
    private short quantity;
    private String sellerName;

}
