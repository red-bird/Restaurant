package com.redbird.restaurant.services;

import com.redbird.restaurant.models.User;

public interface UserService {
    public User findByUsername(String username);
    public User saveUser(User user);
}
