package com.example.shopaccnro.shopaccnroserver.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private String code;
    private String name;
    private String phone;
    private String email;
    private Date dateOfBirth;
    private Integer gender;
    private String address;
    private String image;
    private Integer status; // 1 on, 0 off

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

//    @JsonIgnore
//    @OneToMany(mappedBy = "user")
//    private Set<Bill> bills = new HashSet<Bill>();

}
