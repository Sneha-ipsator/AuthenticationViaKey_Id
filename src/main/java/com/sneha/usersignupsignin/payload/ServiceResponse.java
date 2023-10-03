package com.sneha.usersignupsignin.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class represents a service response that encapsulates the result of a service operation.
 *
 * @param <T> The type of data associated with the response.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceResponse<T> {
    private Boolean success;
    private T data;
    private String message;
}