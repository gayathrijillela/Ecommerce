package com.eshop.sonny.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class DeliveryAddress {

    // delivery address is an address for one order at a time?
    //

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //primary key


    @OneToMany(mappedBy = "billingAddressId", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<Orders> orderbilling; //refer to delivery address

    @OneToMany(mappedBy = "deliveryAddressId", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<Orders> orderdelivery;

    // where is this mapping in the user class?
//    @ManyToOne(fetch=FetchType.LAZY)
//    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
//    private User userId; //ref user table

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "buyer_id", nullable = false)
    private Buyer buyer;


    @Column(nullable = false)
    private String street_address;

    private String address_line_2;
    private String address_line_3;

    @Column(length = 100, nullable = false)
    private String city;

    @Column(length = 20, nullable = false)
    private String postal_code;

    // what is contract number?
    @Column(nullable = false)
    private String contract_number;
    @Column(length = 128)
    private String delivery_notes;

    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}