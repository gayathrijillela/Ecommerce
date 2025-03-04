package com.eshop.sonny.dto.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class SubCategoryResponseDto {

    private String subCategoryName;
    private List<ProductsResponseDto> productsResponseDtoList;


}
