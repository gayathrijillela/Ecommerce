package com.eshop.sonny.repository;

import com.eshop.sonny.model.Disputes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisputesRepository extends JpaRepository<Disputes, Long> {
    //Add custom queries here if needed (please look that up later)
}