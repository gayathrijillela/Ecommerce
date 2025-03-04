package com.eshop.sonny.dto.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@NoArgsConstructor
@Data
public class CartResponseDto {

    private String sessionId;
    private List<CartProductResponseDto> cartProductResponseDtoList;

}
