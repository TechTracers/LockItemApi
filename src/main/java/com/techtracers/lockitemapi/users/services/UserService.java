package com.techtracers.lockitemapi.users.services;

import com.crudjpa.service.impl.CrudService;
import com.techtracers.lockitemapi.security.domain.enums.Roles;
import com.techtracers.lockitemapi.security.domain.models.Role;
import com.techtracers.lockitemapi.security.middleware.JwtUserDetails;
import com.techtracers.lockitemapi.security.persistence.repository.IRoleRepository;
import com.techtracers.lockitemapi.users.domain.model.User;
import com.techtracers.lockitemapi.users.domain.service.IUserService;
import com.techtracers.lockitemapi.users.persistence.repository.IUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService extends CrudService<User, Long> implements IUserService {
    private final IUserRepository userRepository;
    private final IRoleRepository roleRepository;

    public UserService(IUserRepository userRepository, IRoleRepository roleRepository) {
        super(userRepository);
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }


    @Override
    public Optional<User> findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public Optional<User> findFirstAdmin() {
        Optional<Role> role = roleRepository.findByName(Roles.ADMIN);
        if (role.isEmpty())
            return Optional.empty();

        return userRepository.findFirstByRole(role.get());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findUserByUsername(username);
        if (user.isEmpty())
            throw new UsernameNotFoundException("User with username " + username + " not found.");
        return JwtUserDetails.fromUser(user.get());
    }
}
