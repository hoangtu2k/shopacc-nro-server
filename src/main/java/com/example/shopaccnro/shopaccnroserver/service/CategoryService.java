package com.example.shopaccnro.shopaccnroserver.service;

import com.example.shopaccnro.shopaccnroserver.entity.Category;
import com.example.shopaccnro.shopaccnroserver.entity.Product;
import com.example.shopaccnro.shopaccnroserver.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategory() {
        return categoryRepository.getAllCategory();
    }

    public List<Product> getProductsByCategoryId(Long id) {
        return categoryRepository.findById(id)
                .map(Category::getProducts)
                .orElse(new HashSet<>())
                .stream()
                .collect(Collectors.toList());
    }

}
