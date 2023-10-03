package com.sneha.usersignupsignin.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class represents a response indicating the validity of an operation or action.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IsValidResponse {
    private boolean success;
    private String message;
}
