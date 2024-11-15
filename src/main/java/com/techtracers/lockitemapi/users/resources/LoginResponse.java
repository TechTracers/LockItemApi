package com.techtracers.lockitemapi.users.resources;

import com.techtracers.lockitemapi.security.domain.enums.Roles;
import com.techtracers.lockitemapi.security.domain.models.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    private String username;
    private String name;
    private String lastname;
    private String email;
    private String phone;
    private Roles role;
    private String token;

    public void setRole(Role role) {
        this.role = role.getName();
    }

}
