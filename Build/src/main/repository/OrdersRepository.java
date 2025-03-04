package com.eshop.sonny.repository;

import com.eshop.sonny.model.DeliveryAddress;
import com.eshop.sonny.model.Orders;

import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long>, JpaSpecificationExecutor<Orders> {
    @Modifying
    @Transactional
    @Query("UPDATE Orders o SET o.billingAddressId = null WHERE o.billingAddressId = :billingAddressId")
    void setbillingAddressIdNull(@Param("billingAddressId") DeliveryAddress billingAddressId);

    @Modifying
    @Transactional
    @Query("UPDATE Orders o SET o.deliveryAddressId = null WHERE o.deliveryAddressId = :deliveryAddressId")
    void setdeliveryAddressIdNull(@Param("deliveryAddressId") DeliveryAddress deliveryAddressId);


}