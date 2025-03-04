package com.eshop.sonny.repository;

import com.eshop.sonny.model.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BuyerRepository extends JpaRepository<com.eshop.sonny.model.Buyer, String> {

    Buyer findByEmail(String email);

    Buyer findByUserName(String userName);

    //Optional<Buyer> findByUsername(String username);


    @Query("SELECT b FROM Buyer b LEFT JOIN FETCH b.cart c LEFT JOIN FETCH c.cartProducts WHERE b.userName = :userName")
    Optional<Buyer> findByUserNameWithCart(@Param("userName") String userName);

}
