package com.eshop.sonny.model;

import org.hibernate.annotations.CreationTimestamp;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "recommendations", uniqueConstraints = {
        @UniqueConstraint(columnNames = { "user_id",
                "product_id" })
})
public class Recommendation {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "product_id")
  @JsonIgnore
  private Product product;


  @Column(name = "created_at", updatable = false, nullable = false)
  @CreationTimestamp
  private LocalDateTime createdAt;
}