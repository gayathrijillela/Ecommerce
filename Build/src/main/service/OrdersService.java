package com.eshop.sonny.service;

import com.eshop.sonny.QuerySpecification.Spacifications.OrderSpecification;
import com.eshop.sonny.dto.Request.OrderRequestDto;
import com.eshop.sonny.model.Orders;

import java.util.List;

public interface OrdersService {

    boolean createOrder(
            OrderRequestDto orderRequestDto
    );

    // we need to check if the status for the order is allowed to be cancelled?
    boolean cancelOrder(
            String userName,
            Long orderId
    );


    // how do we want to search in orders database?

    public List<Orders> getFilteredOrders(
            OrderSpecification orderSpecification
    );


}
