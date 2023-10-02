package com.sneha.usersignupsignin.repository;

import com.sneha.usersignupsignin.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Products,Integer> {
    public Optional<Products> findByPname(String pname);

}
