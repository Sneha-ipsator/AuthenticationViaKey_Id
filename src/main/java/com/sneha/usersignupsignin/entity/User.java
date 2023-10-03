package com.sneha.usersignupsignin.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

/**
 * This class represents a user entity in the system and implements the UserDetails interface for Spring Security.
 */
@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userId;
    private String userLoginId;
    private String  name;
    private String email;
    private String password;
    @Transient
    private String role;
    private String userLoginkey;

    @ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
    @JoinTable(name="users_roles",joinColumns=@JoinColumn(name="user_id"), inverseJoinColumns =@JoinColumn (name="role_id"))
    private Set<Role> roles=new HashSet<>();


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Role> roles=this.getRoles();
        List<SimpleGrantedAuthority>authorities=new ArrayList<>();
        for(Role role :roles){
            authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getName()));
        }
//        authorities.add(new SimpleGrantedAuthority())
        return authorities;
    }

    @Override
    public String getUsername() {
        return this.userLoginId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getPassword() {
        return this.userLoginkey;
    }

}


