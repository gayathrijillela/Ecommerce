package com.eshop.sonny.mapper.mappersClasses;

import com.eshop.sonny.dto.Request.OrderRequestDto;
import com.eshop.sonny.model.Buyer;
import com.eshop.sonny.model.Cart;
import com.eshop.sonny.model.OrderedProducts;
import com.eshop.sonny.model.Orders;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component

public class OrderMapper {

    private final ProductCartToOrderProductMapper productCartToOrderProductMapper;
    private final AddressMapper deliveryAddressMapper;
    public static Orders OrderRequestToOrder(OrderRequestDto orderRequest, Buyer buyer) {
        Orders order = new Orders();
        order.setBuyer(buyer);
        order.setOrderedProducts(
                OrderMapper.CartProductToOrderProductMapper(buyer.getCart()));
      //  order.setPaymentMethodsId(orderRequest.getPayment4Digits());
        order.setBillingAddressId(
                AddressMapper.toDeliveryAddress(
                orderRequest.getBillingAddressRequestDto()));
        order.setDeliveryAddressId(
                AddressMapper.toDeliveryAddress
                        (orderRequest.getDelivaryAddressRequestDto()));

        // how to calculate total taxes?
        // and is the total price include the taxes?
        // status fields??

        // payment method?

        return order;
    }

    private static List<OrderedProducts> CartProductToOrderProductMapper(Cart buyerCart) {
        return buyerCart.getCartProducts().stream()
                .map(ProductCartToOrderProductMapper::CartProductToOrderProductMapper)
                .collect(Collectors.toList());
    }

}
