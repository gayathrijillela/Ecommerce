package com.eshop.sonny.mapper.mappersClasses;

import com.eshop.sonny.dto.Request.BuyerUpdateDto;
import com.eshop.sonny.dto.Request.UserRegisterDto;
import com.eshop.sonny.model.Buyer;
import com.eshop.sonny.model.Cart;
import com.eshop.sonny.model.enums.Role;
import org.springframework.stereotype.Component;

@Component
public class BuyerMapper {

    public static Buyer BuyerRegisterDtoToBuyer(UserRegisterDto buyerUpdateDto) {

        Buyer buyer = new Buyer();
        buyer.setUserName(buyerUpdateDto.getUserName());
        buyer.setPassword(buyerUpdateDto.getPassword());
        buyer.setEmail(buyerUpdateDto.getEmail());
        buyer.setPhone(buyerUpdateDto.getPhone());
        buyer.setRole(Role.BUYER);
        buyer.setCart(new Cart());
        return buyer;

    }

}
