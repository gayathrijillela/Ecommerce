package com.eshop.sonny.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // primary key

// orders ---> returns -----> returnProduct
// we need to think in this ?



//    // what dose Returned product doing here with orders?
//    @OneToMany(mappedBy = "orderId", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<ReturnedProduct> returnedProducts; //refer to returned products
// is cart product diffrant from order product?
    @OneToMany(mappedBy = "orderId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderedProducts> orderedProducts; //refer to ordered products

//    @OneToMany(mappedBy = "orderId", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Return> returns; //refer to returns

    @OneToMany(mappedBy = "orderId", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<CouponsApplied> couponsApplied; //refer to coupon applied

    // wrong mapped cannot found in both directions
    @OneToMany(mappedBy = "orderId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Disputes> disputes; //refer to disputes


    // where is this mapped in user table
    @ManyToOne(fetch=FetchType.LAZY) //set to null if id is deleted
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private Buyer buyer;
    //private User userId; // ref user table

    //ref paymentmethods table

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "billing_address_id", referencedColumnName = "id", nullable = true) //set this to null if address is deleted
    private DeliveryAddress billingAddressId; //ref paymentmethods table

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "delivery_address_id", referencedColumnName = "id", nullable = true) //set this to null if address is deleted
    private DeliveryAddress deliveryAddressId; //ref paymentmethods table

    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal delivery_fee;
    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal total_price;
    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal tax;
    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal commission;


    // should the status be one for all orders or one for each order product?

    @Column(nullable = false)
    private String status;

    private String paymentStatus;

    @Column(nullable = false)
    private LocalDate date;

    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    private String tracingNumber;

}