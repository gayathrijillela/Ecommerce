package com.eshop.sonny.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.eshop.sonny.model.CouponsApplied;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponsAppliedRepository extends JpaRepository<CouponsApplied, Long> {
}
