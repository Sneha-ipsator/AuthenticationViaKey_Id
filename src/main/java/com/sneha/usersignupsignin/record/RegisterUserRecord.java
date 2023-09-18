package com.sneha.usersignupsignin.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterUserDTO {
    String name;
    String email;
    String password;
    Integer age;
}
