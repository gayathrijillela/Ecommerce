package com.eshop.sonny.service;


import com.eshop.sonny.dto.Response.CartResponseDto;

public interface CartService {

    CartResponseDto getCartByUserName(String userName);
    boolean addProductToCart(String userName, Long productId);
    boolean removeProductFromCart(String userName, Long productId);

}
