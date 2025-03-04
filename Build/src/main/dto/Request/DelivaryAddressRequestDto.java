package com.eshop.sonny.dto.Request;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class DelivaryAddressRequestDto {

    private String streetAddress;
    private String addressLine2;
    private String addressLine3;
    private String city;
    private String postalCode;
    private String contractNumber;
    private String deliveryNotes;

}
