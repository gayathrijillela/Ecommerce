package com.eshop.sonny.repository;

import com.eshop.sonny.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    //Add custom queries here if needed (please look that up later)
}