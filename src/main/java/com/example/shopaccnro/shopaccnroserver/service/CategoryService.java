package com.example.shopaccnro.shopaccnroserver.service;

import com.example.shopaccnro.shopaccnroserver.entity.Category;
import com.example.shopaccnro.shopaccnroserver.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategory() {
        return categoryRepository.getAllCategory();
    }

}
