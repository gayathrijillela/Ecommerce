package com.eshop.sonny.mapper.mappersClasses;

import com.eshop.sonny.dto.Response.CartProductResponseDto;
import com.eshop.sonny.dto.Response.CartResponseDto;
import com.eshop.sonny.model.Cart;
import com.eshop.sonny.model.CartProduct;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CartMapper {

    public static CartResponseDto carttoCartResponseDto(Cart cart) {
        CartResponseDto cartResponseDto = new CartResponseDto();
        cartResponseDto.setCartProductResponseDtoList(
                cartProductTocartProductResponseDtoList(cart.getCartProducts())
        );
        cartResponseDto.setSessionId(cart.getSessionId());
        return cartResponseDto;
    }

    private static List<CartProductResponseDto> cartProductTocartProductResponseDtoList(
            List<CartProduct> cartProducts
    ) {
        List<CartProductResponseDto> cartProductResponseDtoList = new ArrayList<>();
        cartProducts.forEach(cartProduct -> {
            cartProductResponseDtoList.add(new CartProductResponseDto(
                    cartProduct.getProduct().getProductImages(),
                    cartProduct.getProduct().getProductName(),
                    cartProduct.getProduct().getProductPrice(),
                    cartProduct.getQuantity(),
                    cartProduct.getProduct().getSeller().getUsername()
            ));
        });
        return cartProductResponseDtoList;
    }

}
