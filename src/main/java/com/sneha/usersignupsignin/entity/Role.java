package com.sneha.usersignupsignin.entity;

import jakarta.persistence.*;
import lombok.*;


/**
 * This class represents a user role in the system.
 */
@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name ="roles")
public class Role {
    @Id
    @Column(name="role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
}
