package com.eshop.sonny.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eshop.sonny.CompositePrimaryKeys.CompositeOrderAndProductId;
import com.eshop.sonny.model.OrderedProducts;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderedProductsRepository extends JpaRepository<OrderedProducts, CompositeOrderAndProductId> {

}
