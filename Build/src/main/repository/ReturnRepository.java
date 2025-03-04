package com.eshop.sonny.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eshop.sonny.model.Return;

@Repository
public interface ReturnRepository extends JpaRepository<Return, Long> {
}
