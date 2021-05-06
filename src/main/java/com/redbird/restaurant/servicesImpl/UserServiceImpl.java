package com.redbird.restaurant.servicesImpl;

import com.redbird.restaurant.models.Role;
import com.redbird.restaurant.models.User;
import com.redbird.restaurant.repositories.UserRepository;
import com.redbird.restaurant.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Collections;

@Slf4j
@Service
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void initAdmin() {
        if (findByUsername("admin") != null) {
            log.info("Admin credentials has been created\n" +
                    "username: admin\n" +
                    "password: admin");
            return;
        }
        User admin = new User();
        admin.setActive(true);
        admin.setPassword(passwordEncoder.encode("admin"));
        admin.setUsername("admin");
        admin.setRoles(Collections.singleton(Role.ADMIN));
        userRepository.save(admin);
        log.info("Admin credentials has been created\n" +
                "username: admin\n" +
                "password: admin");
    }

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User saveUser(User user) {
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }
}
