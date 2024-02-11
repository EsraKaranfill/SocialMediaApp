package com.esra.repository;

import com.esra.entity.Auth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthRepository extends JpaRepository<Auth,Long> {
    Optional<Auth> findOptionalByEmailContainingIgnoreCase(String value);

    Optional<Auth> findOptionalByUsernameAndPassword(String username, String password);

    Optional<Auth> findOptionalByEmail(String email);
}
