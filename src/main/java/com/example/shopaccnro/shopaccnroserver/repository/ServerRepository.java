package com.example.shopaccnro.shopaccnroserver.repository;

import com.example.shopaccnro.shopaccnroserver.entity.Server;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServerRepository extends JpaRepository<Server, Long> {

    @Query(value = "select s from Server s WHERE s.id = :id")
    Server getById(@Param("id") Long id);

    @Query(value = "select s from Server s")
    List<Server> getAllServer();

}
