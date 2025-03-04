package com.eshop.sonny.repository;

import com.eshop.sonny.model.SavedProducts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SavedProductsRepository extends JpaRepository<SavedProducts, Long> {
    // Add custom queries here if needed (please look that up later)
}
