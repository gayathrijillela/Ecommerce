package com.eshop.sonny.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //If a category is removed or deleted, all its sub categories will also be removed.
    @OneToMany(mappedBy = "categoryId", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore //this is so we don't need to load any subcategories with the same id because we don't need it here (for now)
    private List<SubCategory> subCategories; //category id in subcategory table refers to our id

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
    @JoinColumn(name = "RecommendationId")
    @JsonIgnore
    private List<Product> recommendations;

    private String categoryName;
    private String category_url;
    private String category_banner;
}