package com.project.TaskManager.Security.Request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Data
public class SignUpRequest {
    @NotBlank
    @Size(min = 3, max = 50)
    private String username;

    @NotBlank
    @Size(min = 3, max = 30)
    private String password;

    @NotBlank
    @Email
    @Size(min = 3, max = 50)
    private String email;

    private Set<String> Role;  //SET used coz it stores only unique values
}
