package com.techtracers.lockitemapi.users.domain.service;

import com.crudjpa.service.ICrudService;
import com.techtracers.lockitemapi.users.domain.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;


public interface IUserService extends ICrudService<User, Long>, UserDetailsService {

    Optional<User> findUserByUsername(String username);
}
