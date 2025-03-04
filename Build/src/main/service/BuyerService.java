package com.eshop.sonny.service;

import com.eshop.sonny.dto.Request.BuyerUpdateDto;
import com.eshop.sonny.dto.Request.UserRegisterDto;

public interface BuyerService {

        boolean saveBuyer(UserRegisterDto buyerDto);

        boolean updateBuyer(String userName, BuyerUpdateDto buyerDto);

        boolean deleteBuyer(String userName);

        BuyerUpdateDto getBuyer(String userName);




}
