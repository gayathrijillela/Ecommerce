package com.eshop.sonny.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "products")
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "product_name", nullable = false)
  private String productName;

  @Column(name = "product_description", columnDefinition = "VARCHAR(2048) DEFAULT ''")
  private String productDescription = "";

  @Column(name = "product_price", nullable = false)
  // price String ?
  private double productPrice;
  //private String productPrice;

  @Column(name = "inventory_count", columnDefinition = "INT DEFAULT 0")
  private int inventoryCount = 0;

  // we will need list of images
  @Column(name = "product_images", columnDefinition = "JSON")
  private String productImages = "{}";



  // why ?
//  @Column(name = "subcategory_id", insertable = false, updatable = false)
//  private Long subcategoryId;



  // wrong mapping

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "products", nullable = false)
  @JsonIgnore
  private SubCategory subcategory;

  @Column(name = "product_specifications", columnDefinition = "VARCHAR(2048) DEFAULT ''")
  private String productSpecifications = "";

  @Column(name = "product_keyFeatures", columnDefinition = "VARCHAR(2048) DEFAULT ''")
  private String productKeyFeatures = "";

  @Column(name = "times_viewed", columnDefinition = "INT DEFAULT 0")
  private int timesViewed = 0;

  @Column(name = "total_purchased", columnDefinition = "INT DEFAULT 0")
  private int totalPurchased = 0;

  @Column(name = "average_rating", columnDefinition = "FLOAT DEFAULT 0")
  private float averageRating = 0;

  @Column(name = "reviews_count", columnDefinition = "INT DEFAULT 0")
  private int reviewsCount = 0;


  // do we want to make product has access to all the buyers information


  @ManyToMany(mappedBy = "savedProducts", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<Buyer> savedByUsers;

  @ManyToMany(mappedBy = "purchasedProducts", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<Buyer> purchasedByUsers;


  // where is the seller for the product
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "seller_id", nullable = false)
  @JsonIgnore
  private MySeller seller;






  // wrong mapping
  // what is saved Products?
//  @OneToMany(mappedBy = "productId", cascade = CascadeType.ALL, orphanRemoval = true)
//  @JsonIgnore
//  private List<SavedProducts> savedProducts;


  // wrong mapping
  // why it is here we need to think of something for this
  @OneToMany(mappedBy = "productId", cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonIgnore
  private List<ReturnedProduct> returnedProducts;



  @OneToMany(mappedBy = "productId", cascade = CascadeType.PERSIST)
  @JsonIgnore
  private List<OrderedProducts> orderedProducts;



  @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonIgnore
  private List<CartProduct> cartProducts;


  // wrong mapping
  // what is productsListed? this is a product why it has a product list?
//  @OneToMany(mappedBy = "productId", cascade = CascadeType.ALL, orphanRemoval = true)
//  @JsonIgnore
//  private List<ProductListed> productsListed;

  // wrong mapping
  //?
//  @OneToMany(mappedBy = "productId", cascade = CascadeType.ALL, orphanRemoval = true)
//  @JsonIgnore
//  private List<Recommendation> recommendations;


  // wrong mapping
  @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonIgnore
  private List<Review> reviews;




  // we need to check them working
  @PreRemove
  public void preRemove() {
    // Remove this product from seller's list
    if (seller != null) {
      seller.getProductsListed().remove(this);
    }
    // Remove this product from subcategory's list
    if (subcategory != null) {
      subcategory.getProducts().remove(this);
    }
  }


  @PreUpdate
  public void preUpdate() {
    if (seller != null) {
      // Ensure the seller's product list is updated
      seller.getProductsListed().removeIf(p -> p.getId().equals(this.id));
      seller.getProductsListed().add(this);
    }
    if (subcategory != null) {
      // Ensure the subcategory's product list is updated
      subcategory.getProducts().removeIf(p -> p.getId().equals(this.id));
      subcategory.getProducts().add(this);
    }
  }






}