package com.eshop.sonny.repository;

import com.eshop.sonny.model.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {
    //Add custom queries here if needed (please look that up later)

    boolean existsBySubcategoryName(String subCategoryName);

    SubCategory findBySubcategoryName(String subCategoryName);


}