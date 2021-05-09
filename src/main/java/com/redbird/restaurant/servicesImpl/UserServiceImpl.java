package com.redbird.restaurant.servicesImpl;

import com.redbird.restaurant.models.Role;
import com.redbird.restaurant.models.User;
import com.redbird.restaurant.repositories.UserRepository;
import com.redbird.restaurant.services.MailService;
import com.redbird.restaurant.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final MailService mailService;

    @Value("${spring.mail.active_url}")
    private String url;

//    @PostConstruct
//    public void initAdmin() {
//        if (findByUsername("admin") != null) {
//            log.info("Admin credentials has been created\n" +
//                    "username: admin\n" +
//                    "password: admin");
//            return;
//        }
//        User admin = new User();
//        admin.setActive(true);
//        admin.setPassword(passwordEncoder.encode("admin"));
//        admin.setUsername("admin");
//        admin.setRoles(Collections.singleton(Role.ADMIN));
//        userRepository.save(admin);
//        log.info("Admin credentials has been created\n" +
//                "username: admin\n" +
//                "password: admin");
//    }

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, MailService mailService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.mailService = mailService;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User saveUser(User user) {
        User res = findByUsername(user.getUsername());
        if (res != null) {
            log.info("user " + user.getUsername() + "exists");
            return null;
        }
        user.setRoles(Collections.singleton(Role.USER));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActivationCode(UUID.randomUUID().toString());

        User save = userRepository.save(user);

        sendEmailMessage(save);

        return save;
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public boolean activateUser(String code) {
        User user = userRepository.findByActivationCode(code);

        if (user == null) {
            return false;
        }

        user.setActivationCode(null);
        user.setActive(true);

        userRepository.save(user);

        return true;
    }

    @Override
    public boolean updateProfile(User user, String password, String email) {
        String userEmail = user.getEmail();

        boolean isEmailChanged = (StringUtils.hasText(email) && userEmail != null && !userEmail.equals(email));
        boolean isProfileChanged = false;

        if (isEmailChanged) {
            user.setEmail(email);
            isProfileChanged = true;
            user.setActivationCode(UUID.randomUUID().toString());
        }

        if (StringUtils.hasText(password)) {
            isProfileChanged = true;
            user.setPassword(passwordEncoder.encode(password));
        }

        if (isEmailChanged) {
            sendEmailMessage(user);
        }

        if (isProfileChanged) {
            userRepository.save(user);
        }

        return isProfileChanged;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    private void sendEmailMessage(User save) {
        if (StringUtils.hasText(save.getEmail())) {
            String message = String.format(
                    "Спасибо %s что выбрали нас!\n" +
                            "Осталось подтвердить аккаунт чтобы получить доступ ко всем функциям сайта\n" +
                            "Для активации нужно перейти по этой ссылке: %s/activate/%s" +
                            "\nЕсли вы не регистрировались на сайте, просто проигнорирйте письмо.",
                    save.getUsername(), url, save.getActivationCode()
            );
            mailService.send(save.getEmail(), "Активация аккаунта", message);
            log.info("mail has been sent to " + save.getEmail());
        }
    }

}
