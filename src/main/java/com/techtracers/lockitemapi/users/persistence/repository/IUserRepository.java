package com.techtracers.lockitemapi.users.persistence.repository;

import com.techtracers.lockitemapi.users.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByUsername(String username);
}
