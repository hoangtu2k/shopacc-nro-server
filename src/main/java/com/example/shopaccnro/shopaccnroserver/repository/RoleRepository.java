package com.example.shopaccnro.shopaccnroserver.repository;

import com.example.shopaccnro.shopaccnroserver.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {



}
