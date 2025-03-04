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
public class SubCategory {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id; // primary key

  // wrong mapped here
  @JsonIgnore   // do we need this EAGER ?
  @OneToMany(mappedBy = "subcategory", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Product> products;

  @Column(nullable = false)
  private String subcategoryName;
  private String subcategory_url; // check back later?

  // do we need this banner???
  @Column(length = 2048, nullable = false)
  private String subcategory_banner;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false)
  private Category categoryId; // refers to category table

}