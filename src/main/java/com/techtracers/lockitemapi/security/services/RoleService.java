package com.techtracers.lockitemapi.security.services;

import com.crudjpa.service.impl.CrudService;
import com.techtracers.lockitemapi.security.domain.enums.Roles;
import com.techtracers.lockitemapi.security.domain.models.Role;
import com.techtracers.lockitemapi.security.domain.services.IRoleService;
import com.techtracers.lockitemapi.security.persistence.repository.IRoleRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService extends CrudService<Role, Long> implements IRoleService {
    private final IRoleRepository repository;

    public RoleService(IRoleRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public Optional<Role> findByName(Roles name) {
        return this.repository.findByName(name);
    }
}
