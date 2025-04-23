package com.example.shopaccnro.shopaccnroserver.service;

import com.example.shopaccnro.shopaccnroserver.entity.Category;
import com.example.shopaccnro.shopaccnroserver.entity.Product;
import com.example.shopaccnro.shopaccnroserver.entity.Server;
import com.example.shopaccnro.shopaccnroserver.repository.ProductPhotoRepository;
import com.example.shopaccnro.shopaccnroserver.repository.ProductRepository;
import com.example.shopaccnro.shopaccnroserver.request.ProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;


    @Autowired
    private ProductPhotoRepository productPhotoRepository;

    public List<Product> getAllProduct() {
        return productRepository.getAllProduct();
    }

    public Product getProductById(Long id) {
        return productRepository.getById(id);
    }

    public Product createProduct(ProductRequest productRequest) {
        // Create a new Product instance
        Product product = new Product();

        // Generate a new code automatically if it's null
        if (productRequest.getCode() == null) {
            String generatedCode = generateUserCode();
            product.setCode(generatedCode);
        } else {
            product.setCode(productRequest.getCode());
        }

        // Set other product properties
        product.setName(productRequest.getName());
        product.setPlanet(productRequest.getPlanet());
        product.setRegister(productRequest.getRegister());
        product.setPrice(productRequest.getPrice());
        product.setQuantity(productRequest.getQuantity());
        product.setStatus(1);

        // Set category
        if (productRequest.getCategoryId() != null) {
            product.setCategory(Category.builder().id(productRequest.getCategoryId()).build());
        } else {
            product.setCategory(null);
        }

        // Set server
        if (productRequest.getServerId() != null) {
            product.setServer(Server.builder().id(productRequest.getServerId()).build());
        } else {
            product.setServer(null);
        }

        return productRepository.save(product);
    }

    public Product updateProduct(Long id, ProductRequest productRequest) {
        // Kiểm tra xem sản phẩm có tồn tại không
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (!optionalProduct.isPresent()) {
            throw new RuntimeException("Product not found with id: " + id);
        }
        Product product = optionalProduct.get();

        // Cập nhật các thuộc tính sản phẩm
        if (productRequest.getCode() == null) {
            String generatedCode = generateUserCode();
            product.setCode(generatedCode);
        }
        else {
            product.setCode(productRequest.getCode());
        }
        ;
        product.setName(productRequest.getName());
        product.setPlanet(productRequest.getPlanet());
        product.setRegister(productRequest.getRegister());
        product.setPrice(productRequest.getPrice());
        product.setQuantity(productRequest.getQuantity());

        // Cập nhật danh mục
        if (productRequest.getCategoryId() != null) {
            product.setCategory(Category.builder().id(productRequest.getCategoryId()).build());
        } else {
            product.setCategory(null);
        }

        // Set server
        if (productRequest.getServerId() != null) {
            product.setServer(Server.builder().id(productRequest.getServerId()).build());
        } else {
            product.setServer(null);
        }

        // Lưu sản phẩm
        return productRepository.save(product);
    }

    public Product deleteProduct(Long id, ProductRequest productRequest) {
        // Kiểm tra xem sản phẩm có tồn tại không
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (!optionalProduct.isPresent()) {
            throw new RuntimeException("Product not found with id: " + id);
        }
        Product product = optionalProduct.get();
        product.setStatus(0);

        // Set server
        if (productRequest.getServerId() != null) {
            product.setServer(Server.builder().id(productRequest.getServerId()).build());
        } else {
            product.setServer(null);
        }

        // Lưu sản phẩm
        return productRepository.save(product);
    }

    public Product resetStatus(Long id, ProductRequest productRequest) {
        // Kiểm tra xem sản phẩm có tồn tại không
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (!optionalProduct.isPresent()) {
            throw new RuntimeException("Product not found with id: " + id);
        }
        Product product = optionalProduct.get();
        product.setStatus(1);

        // Set server
        if (productRequest.getServerId() != null) {
            product.setServer(Server.builder().id(productRequest.getServerId()).build());
        } else {
            product.setServer(null);
        }

        // Lưu sản phẩm
        return productRepository.save(product);
    }

    private String generateUserCode() {
        return UUID.randomUUID().toString(); // Example using UUID
    }

}
