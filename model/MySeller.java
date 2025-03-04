package com.eshop.sonny.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MySeller extends MyUser {

    private Boolean enabled;
    @Column(name = "business_name")
    private String businessName;

    @Column(name = "business_address")
    private String businessAddress;

    @Column(name = "business_address_line_2")
    private String businessAddressLine2;

    @Column(name = "business_address_line_3")
    private String businessAddressLine3;

    @Column(name = "business_city", length = 100)
    private String businessCity;

    @Column(name = "business_postal_code", length = 20)
    private String businessPostalCode;

    @Column(name = "biz_cert_number_ein")
    private String bizCertNumberEIN;

// is seller need to know all these information about the buyer??
//    @OneToMany(mappedBy = "seller", orphanRemoval = true)
//    @JsonIgnore
//    private List<Customer> customers;

    @OneToMany(mappedBy = "seller", orphanRemoval = true)
    @JsonIgnore
    private List<Product> productsListed;



}
