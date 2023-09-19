package com.sneha.usersignupsignin.entity;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class JwtResponse {

    private  String jwtToken;
    private  String username;
}
