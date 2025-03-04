package com.eshop.sonny.dto.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductsResponseDto {

    private Long id;
    private String productName;
   // private String productImage;
    private Double productPrice;


}
