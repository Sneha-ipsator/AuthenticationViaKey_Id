package com.sneha.usersignupsignin.repository;

import com.sneha.usersignupsignin.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
/**
 * Repository interface for accessing product data in the database.
 */
@Repository
public interface ProductRepository extends JpaRepository<Products,Integer> {
    /**
     * Find a product by its name.
     *
     * @param pname The name of the product to search for.
     * @return An optional containing the product with the specified name, or an empty optional if not found.
     */
    public Optional<Products> findByPname(String pname);

}
