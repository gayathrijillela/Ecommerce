package com.eshop.sonny.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

import com.eshop.sonny.CompositePrimaryKeys.CompositeOrderAndProductId;
import com.fasterxml.jackson.annotation.JsonProperty;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "returned_products")
public class ReturnedProduct {

    @EmbeddedId
    private CompositeOrderAndProductId id;

    // Map the order_id to the Order entity using @MapsId
//    @ManyToOne(fetch = FetchType.LAZY)
//    @MapsId("order_id")
//    @JoinColumn(name = "order_id", referencedColumnName = "id", nullable = false)
//    private Orders orderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "return_id", referencedColumnName = "id", nullable = false)
    private Return returnId;
    // Map the product_id to the Product entity using @MapsId
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("product_id")
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
    // you are here will save the id for the product in the table but actualy you have the full product
    private Product productId;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "date_received")
    private LocalDateTime dateReceived;

    @Column(name = "return_condition", columnDefinition = "TEXT")
    private String returnCondition;

    @Column(name = "is_eligible_for_resale", nullable = false)
    @JsonProperty
    private boolean isEligibleForResale = false; // Default to false

}