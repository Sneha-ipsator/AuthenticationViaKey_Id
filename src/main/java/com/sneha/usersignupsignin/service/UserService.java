package com.sneha.usersignupsignin.service;

import com.sneha.usersignupsignin.payload.ServiceResponse;
import com.sneha.usersignupsignin.record.RegisterUserRecord;
import com.sneha.usersignupsignin.entity.User;

import java.util.List;

/**
 * Service interface for managing user-related operations.
 */
public interface UserService {

    /**
     * Register a new user with the provided user registration record.
     *
     * @param registerUserRecord The record containing user registration information.
     * @return A service response indicating the success or failure of the registration.
     */
    public ServiceResponse<String> registerUser(RegisterUserRecord registerUserRecord);

    /**
     * Get a list of all users.
     *
     * @return A list of user entities.
     */
    public List<User> getUsers();

    /**
     * Get a user by their login ID.
     *
     * @param id The login ID of the user to retrieve.
     * @return The user entity with the specified login ID.
     */
    User getUserByLoginId(String id);
}
