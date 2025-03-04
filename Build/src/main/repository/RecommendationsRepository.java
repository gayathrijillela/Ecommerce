
package com.eshop.sonny.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.eshop.sonny.model.Recommendation;

@Repository
public interface RecommendationsRepository extends JpaRepository<Recommendation, Long> {



}