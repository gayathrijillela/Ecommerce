package com.eshop.sonny.repository;

import com.eshop.sonny.model.MySeller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SellerRepository extends JpaRepository<MySeller, String> {

    boolean existsByBusinessName(String businessName);
    boolean existsByEmail(String email);

    MySeller findByBusinessName(String businessName);

}
