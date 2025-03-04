package com.eshop.sonny.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Buyer extends MyUser {
    @Column(name = "address")
    private String address;

    @Column(name = "address_line_2")
    private String addressLine2;

    @Column(name = "address_line_3")
    private String addressLine3;

    @Column(name = "city", length = 100)
    private String city;

    @Column(name = "postal_code", length = 20)
    private String postalCode;

    @OneToOne(mappedBy = "buyer", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Cart cart;

    @OneToMany(mappedBy = "buyer", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<CouponsApplied> couponsApplied;


    // not yet completed
    @OneToMany(mappedBy = "buyer", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Orders> orders;


    @ManyToMany
    @JoinTable(
            name = "user_saved_products",
            joinColumns = @JoinColumn(name = "buyer_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> savedProducts;

    @ManyToMany
    @JoinTable(
            name = "user_purchased_products",
            joinColumns = @JoinColumn(name = "buyer_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> purchasedProducts;

    @OneToMany(mappedBy = "buyer", orphanRemoval = true)
    @JsonIgnore
    private List<Return> returns;

    @OneToMany(mappedBy = "buyer", orphanRemoval = true)
    @JsonIgnore
    private List<Review> reviews;

    @Column(name = "number_of_orders", columnDefinition = "INT DEFAULT 0", nullable = false)
    private int numberOfOrders;

    @Column(name = "total_spending", columnDefinition = "INT DEFAULT 0", nullable = false)
    private double totalSpending;

    private double averageOrderAmount;

    private LocalDateTime firstPurchaseDate;

    private LocalDateTime lastPurchaseDate;

    @OneToMany(mappedBy = "buyer", orphanRemoval = true, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<DeliveryAddress> deliveryAddresses;


    @OneToMany(mappedBy = "buyer", cascade = CascadeType.PERSIST)
    @JsonIgnore
    private List<Disputes> disputesList;





}
