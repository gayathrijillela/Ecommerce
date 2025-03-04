package com.eshop.sonny.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.eshop.sonny.CompositePrimaryKeys.CompositeOrderAndProductId;
import org.springframework.boot.autoconfigure.web.WebProperties;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ordered_products")
public class OrderedProducts {


//    @EmbeddedId
//    private CompositeOrderAndProductId id;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Map the order_id to the Order entity using @MapsId
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id",  nullable = false)
    private Orders orderId;

    // Map the product_id to the Product entity using @MapsId
    @ManyToOne(fetch = FetchType.LAZY)
    //@MapsId("product_id")
    @JoinColumn(name = "product_id",  nullable = false)
    private Product productId;

    @Column(name = "quantity", nullable = false)
    private int quantity;

//    @Column(name = "price", nullable = false, precision = 10, scale = 2)
//    private BigDecimal price;

    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = createdAt;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
