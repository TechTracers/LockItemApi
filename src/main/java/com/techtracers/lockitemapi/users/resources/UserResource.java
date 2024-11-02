package com.techtracers.lockitemapi.users.resources;

import com.techtracers.lockitemapi.security.domain.enums.Roles;
import com.techtracers.lockitemapi.security.domain.models.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;

@Data()
@NoArgsConstructor
@AllArgsConstructor
public class UserResource {
    private Long id;
    private String username;
    private String name;
    private String lastname;
    private String email;
    private String phone;
    private Role role;

    public Roles getRole() {
        return Arrays.stream(Roles.values())
                .filter(role -> role == this.role.getName())
                .findFirst()
                .orElseThrow();
    }
}
