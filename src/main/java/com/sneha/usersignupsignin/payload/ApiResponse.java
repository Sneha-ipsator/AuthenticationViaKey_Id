package com.sneha.usersignupsignin.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class represents a generic API response.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse {
    private String status;
    private Object data;
    private Error error;
}
