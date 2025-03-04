package com.eshop.sonny.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eshop.sonny.CompositePrimaryKeys.CompositeOrderAndProductId;
import com.eshop.sonny.model.ReturnedProduct;

@Repository
public interface ReturnedProductRepository extends JpaRepository<ReturnedProduct, CompositeOrderAndProductId> {
}
