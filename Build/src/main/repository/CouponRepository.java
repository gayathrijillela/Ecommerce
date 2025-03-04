package com.eshop.sonny.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.eshop.sonny.model.Coupon;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long> {
}
