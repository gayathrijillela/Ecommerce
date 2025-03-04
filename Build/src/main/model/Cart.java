package com.eshop.sonny.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import lombok.*;
import jakarta.persistence.*;

@Data
@Entity
@Table(name = "carts")
public class Cart {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  // user and userId ?
  // column name is user_id is appear twice
//  @Column(name = "user_id", insertable = false, updatable = false)
//  private String userId;


  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  @JsonIgnore
  private Buyer buyer;
  // column name is user_id is appear twice
  //private User user;

  // why we need sessionId?
  @Column(name = "session_id", nullable = false)
  private String sessionId;
  // incorrect mapped, it should be cart not CartId
  @OneToMany(mappedBy = "cart", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JsonIgnore
  // should we make the cartProduct limited ?
  private List<CartProduct> cartProducts;

  // total price for the cart
  // we need to add it to service
  @Column(name = "total_price", nullable = false)
  private double totalPrice;


}