package com.redbird.restaurant.services;

import com.redbird.restaurant.models.User;

import java.util.List;

public interface UserService {
    public List<User> findAll();
    public User findByUsername(String username);
    public User saveUser(User user);
    public User updateUser(User user);

    boolean activateUser(String code);
}
