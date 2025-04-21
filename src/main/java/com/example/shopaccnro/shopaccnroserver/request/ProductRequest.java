package com.example.shopaccnro.shopaccnroserver.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Setter @Getter
public class ProductRequest {
    private Long id;
    private String code;
    private String barcode;
    private String name;
    private Integer register;
    private String planet;
    private String description;
    private BigDecimal price;
    private Integer quantity;
    private Integer status;
    private Boolean mainImage;
    private List<String> notMainImages; // List of non-main image URLs
    private String imageUrl;
    private Long productId;
    private Long categoryId;
    private String categoryName;
    private Long serverId;
    private String serverName;

}
