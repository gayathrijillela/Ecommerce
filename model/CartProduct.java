package com.eshop.sonny.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "cart_products")
public class CartProduct {
  @Id
  @Column(name = "cartProduct_id", insertable = false, updatable = false)
  // column name is cart_id
  private Long cartProductId;

  // do we need to o from cartproduct to cart?
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "cart_id", insertable = false, updatable = false)
  @JsonIgnore
  // column name is cart_id
  private Cart cart;

  // why we need productId
  // and the same name column name
  // 2 IDs lol
//  @Id
//  @Column(name = "product_id", insertable = false, updatable = false)
//  private Long productId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "product_id", insertable = false, updatable = false)
  @JsonIgnore
  private Product product;
  // why we need productName as we have product?
//  @Column(name = "product_name", nullable = false)
//  private String productName;

//  @Column(name = "price", columnDefinition = "DECIMAL(10,2) NOT NULL", nullable = false)
//  private double price;

  @Column(name = "quantity", nullable = false)
  //  private Long quantity;
  // why Long for the quantity ?
  private short quantity;
//
//  // seller and sellerId?
//  @Column(name = "seller_id", updatable = false)
//  private String sellerId;

//  @ManyToOne(fetch = FetchType.LAZY)
//  @JoinColumn(name = "seller_id", insertable = false, updatable = false)
//  @JsonIgnore
//  private User seller;

}