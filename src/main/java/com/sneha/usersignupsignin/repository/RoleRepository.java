package com.sneha.usersignupsignin.repository;

import com.sneha.usersignupsignin.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for accessing role data in the database.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {
    /**
     * Find a role by its name.
     *
     * @param name The name of the role to search for.
     * @return An optional containing the role with the specified name, or an empty optional if not found.
     */
    public Optional<Role> findByName(String name);

}
