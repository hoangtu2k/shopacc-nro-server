package com.example.shopaccnro.shopaccnroserver.controller;

import com.example.shopaccnro.shopaccnroserver.entity.ProductDetails;
import com.example.shopaccnro.shopaccnroserver.entity.ProductPhoto;
import com.example.shopaccnro.shopaccnroserver.request.ProductRequest;
import com.example.shopaccnro.shopaccnroserver.service.ProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin/productdetail")
public class ProductDetailRest {

    @Autowired
    private ProductDetailService productDetailService;

    @GetMapping()
    public ResponseEntity<List<ProductRequest>> getAllProductDetails() {
        List<ProductDetails> productDetails = productDetailService.getAllProductDetails();

        // Convert the list of Product to ProductReq
        List<ProductRequest> productRequests = productDetails.stream()
                .map(productDetail -> {
                    ProductRequest productRequest = new ProductRequest();
                    productRequest.setId(productDetail.getId());
                    productRequest.setCode(productDetail.getProduct().getCode());
                    productRequest.setBarcode(productDetail.getProduct().getBarcode());
                    productRequest.setName(productDetail.getProduct().getName());
                    productRequest.setPlanet(productDetail.getProduct().getPlanet());
                    productRequest.setRegister(productDetail.getProduct().getRegister());
                    productRequest.setServer(productDetail.getProduct().getServer());
                    productRequest.setPrice(productDetail.getPrice());
                    productRequest.setDescription(productDetail.getProduct().getDescription());
                    productRequest.setStatus(productDetail.getProduct().getStatus());
                    productRequest.setQuantity(productDetail.getQuantity());

                    // Set category details if available
                    if (productDetail.getProduct().getCategory() != null) {
                        productRequest.setCategoryId(productDetail.getProduct().getCategory().getId());
                        productRequest.setCategoryName(productDetail.getProduct().getCategory().getName());
                    } else {
                        productRequest.setCategoryId(null);
                        productRequest.setCategoryName(null);
                    }

                    // Set main image and image URL
                    if (productDetail.getProduct().getProductPhotos() != null && !productDetail.getProduct().getProductPhotos().isEmpty()) {
                        ProductPhoto mainPhoto = productDetail.getProduct().getProductPhotos().stream()
                                .filter(ProductPhoto::getMainImage)
                                .findFirst()
                                .orElse(null);

                        if (mainPhoto != null) {
                            productRequest.setMainImage(true);
                            productRequest.setImageUrl(mainPhoto.getImageUrl());
                        } else {
                            productRequest.setMainImage(false);
                            productRequest.setImageUrl(null);
                        }

                        // Collect non-main image URLs
                        List<String> notMainImageUrls = productDetail.getProduct().getProductPhotos().stream()
                                .filter(photo -> !photo.getMainImage())
                                .map(ProductPhoto::getImageUrl) // Assuming getImageUrl() exists
                                .collect(Collectors.toList());

                        productRequest.setNotMainImages(notMainImageUrls);
                    } else {
                        productRequest.setMainImage(false);
                        productRequest.setImageUrl(null); // No photos available
                        productRequest.setNotMainImages(new ArrayList<>()); // Ensure empty list
                    }

                    return productRequest;
                })
                .collect(Collectors.toList());

        // Return response based on the presence of productReqs
        if (productRequests.isEmpty()) {
            return ResponseEntity.noContent().build(); // Return 204 if no products
        }
        return ResponseEntity.ok(productRequests); // Return 200 and the list of products
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDetails> findById(@PathVariable Long id) {
        ProductDetails productDetails = productDetailService.getProductDetailsById(id);

        if (productDetails != null) {
            return ResponseEntity.ok(productDetails);
        } else {
            // Nếu không tìm thấy, trả về HttpStatus 404 (Not Found)
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update-quantity/increase/{id}")
    public ResponseEntity<ProductDetails> updateProductDetailreduceQuantityService(@PathVariable Long id ,@RequestBody ProductRequest productRequest) {
        try {
            ProductDetails updatedProductdetails = productDetailService.updateProductDetailreduceQuantityService(id, productRequest);
            return ResponseEntity.ok(updatedProductdetails); // Trả về 200 OK và đối tượng sản phẩm đã cập nhật
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Trả về 404 Not Found nếu không tìm thấy sản phẩm

        }
    }

    @PutMapping("/update-quantity/reduce/{id}")
    public ResponseEntity<ProductDetails> updateProductDetailincreaseQuantityService(@PathVariable Long id ,@RequestBody ProductRequest productRequest) {
        try {
            ProductDetails updatedProductdetails = productDetailService.updateProductDetailincreaseQuantityService(id, productRequest);
            return ResponseEntity.ok(updatedProductdetails); // Trả về 200 OK và đối tượng sản phẩm đã cập nhật
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Trả về 404 Not Found nếu không tìm thấy sản phẩm

        }
    }

    @PutMapping("/delete-productdetail-update-quantity/{id}")
    public ResponseEntity<ProductDetails> deleteProductDetailUpdateQuantity(@PathVariable Long id ,@RequestBody ProductRequest productRequest) {
        try {
            ProductDetails updatedProductdetails = productDetailService.deleteProductDetailUpdateQuantity(id, productRequest);
            return ResponseEntity.ok(updatedProductdetails); // Trả về 200 OK và đối tượng sản phẩm đã cập nhật
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Trả về 404 Not Found nếu không tìm thấy sản phẩm

        }
    }

}
