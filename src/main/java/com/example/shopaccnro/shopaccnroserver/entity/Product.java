package com.example.shopaccnro.shopaccnroserver.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String name;
    private String register;
    private String planet;
    private Integer quantity;
    private BigDecimal price;
    private String description;
    private Integer status;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "server_id")
    private Server server;

    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private Set<ProductPhoto> productPhotos = new HashSet<ProductPhoto>();


}
