package com.eshop.sonny.repository;

import com.eshop.sonny.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    //Add custom queries here if needed (please look that up later)
    // ???

    boolean existsByCategoryName(String name);

    Category findByCategoryName(String name);


}