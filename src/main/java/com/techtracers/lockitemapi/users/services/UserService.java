package com.techtracers.lockitemapi.users.services;

import com.crudjpa.service.impl.CrudService;
import com.techtracers.lockitemapi.security.middleware.JwtUserDetails;
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

    public UserService(IUserRepository userRepository) {
        super(userRepository);
        this.userRepository = userRepository;
    }


    @Override
    public Optional<User> findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findUserByUsername(username);
        if (user.isEmpty())
            throw new UsernameNotFoundException("User with username " + username + " not found.");
        return JwtUserDetails.fromUser(user.get());
    }
}
