package com.techtracers.lockitemapi.users.resources;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.techtracers.lockitemapi.security.domain.enums.Roles;
import com.techtracers.lockitemapi.security.domain.models.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserResource {
    @NotBlank
    @Size(max = 30)
    private String username;

    @NotBlank
    @Size(max = 30)
    private String password;

    @NotBlank
    @Size(max = 30)
    private String name;

    @NotBlank
    @Size(max = 30)
    private String lastname;

    @NotBlank
    @Email
    @Size(max = 50)
    private String email;

    @NotBlank
    @Size(max = 9)
    private String phone;

    @JsonIgnore
    private Role mRole;

    private Object role = Roles.NORMAL;
}
