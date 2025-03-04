package com.eshop.sonny.mapper.mappersClasses;

import com.eshop.sonny.dto.Request.UserRegisterDto;
import com.eshop.sonny.model.MySeller;
import com.eshop.sonny.model.enums.Role;
import org.springframework.stereotype.Component;

@Component

public class SellerMapper {

    public static MySeller SellerRegisterDtoToSeller(UserRegisterDto sellerUpdateDto) {

        MySeller seller = new MySeller();
        seller.setUserName(sellerUpdateDto.getUserName());
        seller.setPassword(sellerUpdateDto.getPassword());
        seller.setEmail(sellerUpdateDto.getEmail());
        seller.setPhone(sellerUpdateDto.getPhone());
        seller.setRole(Role.SELLER);
        return seller;

    }

}
