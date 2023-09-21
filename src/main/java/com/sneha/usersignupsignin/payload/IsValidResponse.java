package com.sneha.usersignupsignin.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IsValidResponse {
    private boolean success;
    private String message;
}
