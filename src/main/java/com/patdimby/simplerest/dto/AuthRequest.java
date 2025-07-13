package com.patdimby.simplerest.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AuthRequest {

    @Email(message = "Invalid email address")
    @NotBlank(message = "Email address is mandatory")
    private String email;

    @NotBlank(message = "Password cannot be blank")
    private String password;
}
