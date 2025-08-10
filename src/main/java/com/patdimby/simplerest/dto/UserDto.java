package com.patdimby.simplerest.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.patdimby.simplerest.model.Role;
import com.patdimby.simplerest.model.Sex;
import com.patdimby.simplerest.model.UserRole;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {

    private Long id;

    @NotEmpty(message = "Name is mandatory.")
    private String firstName;

    private String lastName;

    private String username;

    @NotEmpty(message = "Email is mandatory")
    private String email;

    @NotBlank(message = "Password cannot be blank")
    @Size(min = 5, message = "Password must be {min} length.")
    private String password;

    private String confirmpassword;

    private UserRole role; // ROLE_USER, ROLE_ADMIN

    private Sex sex; // MALE, FEMALE

    private Collection<Role> roles;

    @Override
    public String toString() {
        return "UserDto{" +
                "id='" + id + '\'' +
                ", name='" + firstName + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
