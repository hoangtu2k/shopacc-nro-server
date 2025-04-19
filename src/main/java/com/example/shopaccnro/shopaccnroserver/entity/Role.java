package com.example.shopaccnro.shopaccnroserver.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private  String name;
    private  Integer status; // 1 on, 0 off

    @JsonIgnore
    @OneToMany(mappedBy = "role")
    private Set<Account> accounts = new HashSet<Account>();

}
