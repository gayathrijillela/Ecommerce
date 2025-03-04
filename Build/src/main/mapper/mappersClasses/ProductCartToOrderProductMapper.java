package com.eshop.sonny.mapper.mappersClasses;

import com.eshop.sonny.model.CartProduct;
import com.eshop.sonny.model.OrderedProducts;
import org.springframework.stereotype.Component;


@Component

public class ProductCartToOrderProductMapper {

    public static OrderedProducts CartProductToOrderProductMapper(CartProduct cartProduct) {

        OrderedProducts orderedProducts = new OrderedProducts();
        orderedProducts.setProductId(cartProduct.getProduct());
        orderedProducts.setQuantity(cartProduct.getQuantity());
        return orderedProducts;

    }


}
