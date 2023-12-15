package com.parkauto.parkauto.dto;

import lombok.Data;

@Data
public class JwtAuthenticationResponse {
    private String token;
    private String refreshToken;
    private String email;
    private  String firstName;
    private  String lastName;
    private String profileImageURL;

}
