package com.eshop.sonny.repository;

import com.eshop.sonny.model.Admin;
import com.eshop.sonny.model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, String> {

    boolean existsByUserName(String userName);

    void deleteByUserName(String userName);

    //Admin findByUserName(String userName);

    //@Query("SELECT a FROM Admin a WHERE a.username = :username")
    Optional<Admin> findByUserName(String username);




}
