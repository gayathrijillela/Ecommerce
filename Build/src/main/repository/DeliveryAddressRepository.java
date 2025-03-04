package com.eshop.sonny.repository;

import com.eshop.sonny.model.DeliveryAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryAddressRepository extends JpaRepository<DeliveryAddress, Long> {
    //Add custom queries here if needed (please look that up later)
}