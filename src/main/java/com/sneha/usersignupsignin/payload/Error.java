package com.sneha.usersignupsignin.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class represents an error message.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Error {
    private String message;
}
