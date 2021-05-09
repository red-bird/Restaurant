package com.redbird.restaurant.repositories;

import com.redbird.restaurant.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsernameOrEmail(String username, String email);
    User findByUsername(String username);
    User findByActivationCode(String code);
}
