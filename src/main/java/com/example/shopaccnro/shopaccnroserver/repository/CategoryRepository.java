package com.example.shopaccnro.shopaccnroserver.repository;

import com.example.shopaccnro.shopaccnroserver.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query(value = "select c from Category c WHERE c.id = :id")
    Category getById(@Param("id") Long id);

    @Query(value = "select c from Category c")
    List<Category> getAllCategory();

}
