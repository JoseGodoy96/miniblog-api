package com.chema.db.miniblog.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {

    @NotBlank(message = "username is required")
    private String username;

    @Email(message = "Email must be valid")
    @NotBlank(message = "email is required")
    private String email;
}
