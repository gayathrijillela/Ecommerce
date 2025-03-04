package com.eshop.sonny.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "coupons")
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // if we delete the coupon we need to delete the applied coupons?
    @OneToMany(mappedBy = "couponId", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<CouponsApplied> couponsApplied; //category id in subcategory table refers to our id

    @Column(name = "code", unique = true, nullable = false, length = 20)
    private String code;

    @Column(name = "discount_amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal discountAmount;

    @Column(name = "expiration_date", nullable = false)
    private LocalDate expirationDate;

    @Column(name = "usage_limit")
    private Integer usageLimit;

    @Column(name = "usage_limit_per_user")
    private Integer usageLimitPerUser;

    @Column(name = "min_purchase_amount", precision = 10, scale = 2)
    private BigDecimal minPurchaseAmount;

    // @Column(name = "applicanle_products")
    // @ElementCollection
    // private List<String> applicableProducts;

    // @Column(name = "applicanle_categories")
    // @ElementCollection
    // private List<String> applicableCategories;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "usage_count", nullable = false)
    @JsonProperty
    private int usageCount = 0;

    // @ElementCollection
    // @Column(name = "applicability_conditions")
    // private Map<String, String> applicabilityConditions;

    @Column(name = "notes", columnDefinition = "TEXT")
    private String notes;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}