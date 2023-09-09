package com.sneha.usersignupsignin.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userId;
    private String username;
    private String password;
    private String gender;
    @Column(name = "`key`")
    private String key;

//    private String email;
//    private String mob;
//    private String dob;
//    @ElementCollection(targetClass = String.class)
//    private List<String> technology;



}
