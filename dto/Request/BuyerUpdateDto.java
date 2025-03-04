package com.eshop.sonny.dto.Request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BuyerUpdateDto {

    private String address;
    private String addressLine2;
    private String addressLine3;
    private String city;
    private String postalCode;
    private String phone;
    private String email;
    private String password;
    private String userName;
    private String profilePicture;
    private String bizCertNumberEIN;


}
