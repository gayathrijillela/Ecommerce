package com.eshop.sonny.service;

import com.eshop.sonny.dto.Request.BuyerUpdateDto;
import com.eshop.sonny.dto.Request.UserRegisterDto;

public interface SellerService {
    boolean saveSeller(UserRegisterDto sellerDto);
    boolean updateSeller(BuyerUpdateDto buyerDto);
    boolean deleteSeller(String bussinessName);

    boolean getSpacificSeller(String bussinessName);

}
