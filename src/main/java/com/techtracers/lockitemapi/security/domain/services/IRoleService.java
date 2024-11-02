package com.techtracers.lockitemapi.security.domain.services;

import com.crudjpa.service.ICrudService;
import com.techtracers.lockitemapi.security.domain.enums.Roles;
import com.techtracers.lockitemapi.security.domain.models.Role;

import java.util.Optional;

public interface IRoleService extends ICrudService<Role, Long> {
    Optional<Role> findByName(Roles name);
}
