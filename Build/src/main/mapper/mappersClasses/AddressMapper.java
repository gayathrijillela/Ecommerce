package com.eshop.sonny.mapper.mappersClasses;

import com.eshop.sonny.dto.Request.DelivaryAddressRequestDto;
import com.eshop.sonny.model.DeliveryAddress;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {

    public static DeliveryAddress toDeliveryAddress(DelivaryAddressRequestDto dto) {
        DeliveryAddress address = new DeliveryAddress();
        address.setStreet_address(dto.getStreetAddress());
        address.setAddress_line_2(dto.getAddressLine2());
        address.setAddress_line_3(dto.getAddressLine3());
        address.setCity(dto.getCity());
        address.setPostal_code(dto.getPostalCode());
        if(dto.getDeliveryNotes() != null)
            address.setDelivery_notes(dto.getDeliveryNotes());
        return address;
    }

}
