package com.techtracers.lockitemapi.users.domain.service;

import com.crudjpa.service.ICrudService;
import com.techtracers.lockitemapi.users.domain.model.User;

import java.util.Optional;


public interface IUserService extends ICrudService<User, Long> {
    Optional<User> findUserByUsernameAndPassword(String username, String password);

    Optional<User> findUserByUsername(String username);
}
