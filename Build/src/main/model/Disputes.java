package com.eshop.sonny.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import com.eshop.sonny.model.enums.SettlementType;
import com.eshop.sonny.model.enums.StatusType;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity


public class Disputes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // where is the mapped in user table ?
//    @ManyToOne(fetch=FetchType.LAZY)
//    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
//    private User userId; //ref user table (id is added on the name)

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "buyer_id", referencedColumnName = "id", nullable = false)
    private Buyer buyer;


    // wrong mapped cannot found in both directions
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "order_id", referencedColumnName = "id", nullable = false)
    private Orders orderId; //ref order table (id is added on the name)

    @Enumerated(EnumType.ORDINAL)//needs to be number
    private SettlementType settlement_type;

    @Enumerated(EnumType.ORDINAL)//needs to be number
    @Column(nullable = false)
    private StatusType status;

    @Lob //text type
    // is it this big to make it @Lob
    @Column(nullable = false)
    private String description;

    @Lob
    // is it this big to make it @Lob
    private String text;
}