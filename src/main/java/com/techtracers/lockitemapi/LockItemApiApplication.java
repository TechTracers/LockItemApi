package com.techtracers.lockitemapi;

import com.techtracers.lockitemapi.security.domain.enums.Roles;
import com.techtracers.lockitemapi.security.domain.models.Role;
import com.techtracers.lockitemapi.security.domain.services.IRoleService;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LockItemApiApplication {

    IRoleService roleService;

    public LockItemApiApplication(IRoleService roleService) {
        this.roleService = roleService;
    }

    @PostConstruct
    private void init() {
        try {
            for (Roles roles : Roles.values()) {
                Role role = Role.builder()
                        .name(roles)
                        .build();
                roleService.save(role);
            }

        } catch (Exception ignored) {
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(LockItemApiApplication.class, args);
    }

}
