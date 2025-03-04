package com.eshop.sonny.dto.Request;

import com.eshop.sonny.dto.Response.CartProductResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Data
public class OrderRequestDto {

    private String userName;

    private DelivaryAddressRequestDto delivaryAddressRequestDto;

    private DelivaryAddressRequestDto billingAddressRequestDto;

    private String payment4Digits;


}
