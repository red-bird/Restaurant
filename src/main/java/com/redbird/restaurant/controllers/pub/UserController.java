package com.redbird.restaurant.controllers.pub;

import com.redbird.restaurant.models.User;
import com.redbird.restaurant.models.dto.CaptchaResponseDto;
import com.redbird.restaurant.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Map;

@Controller
@RequestMapping
public class UserController {

    private final static String CAPTCHA_URL = "https://www.google.com/recaptcha/api/siteverify?secret=%s&response=%s";

    private final UserService userService;
    private final RestTemplate restTemplate;

    @Value("${recaptcha.client_secret}")
    private String client_secret;
    @Value("${recaptcha.server_secret}")
    private String server_secret;

    @Autowired
    public UserController(UserService userService, RestTemplate restTemplate) {
        this.userService = userService;
        this.restTemplate = restTemplate;
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("key", client_secret);
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(
            @RequestParam("passwordConfirm") String passwordConfirm,
            @RequestParam("g-recaptcha-response") String captchaResponse,
            @Valid User user,
            BindingResult bindingResult,
            Model model
    ) {
        String url = String.format(CAPTCHA_URL, server_secret, captchaResponse);
        CaptchaResponseDto response = restTemplate.postForObject(url, Collections.emptyList(), CaptchaResponseDto.class);

        if (!response.isSuccess()) {
            model.addAttribute("captchaError", "Подтвердите капчу");
        }

        boolean isConfirmEmpty = !StringUtils.hasText(passwordConfirm);

        if (isConfirmEmpty) {
            model.addAttribute("passwordConfirmError", "Подтверждение пароля не может быть пустыми");
        }

        if (user.getPassword() != null && passwordConfirm != null
                && !user.getPassword().equals(passwordConfirm)) {
            model.addAttribute("passwordError", "Пароли различаются");
            model.addAttribute("key", client_secret);
            return "registration";
        }

        if (isConfirmEmpty || bindingResult.hasErrors() || !response.isSuccess()) {
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);

            model.mergeAttributes(errors);
            model.addAttribute("key", client_secret);
            return "registration";
        }

        user = userService.saveUser(user);
        if (user == null) {
            model.addAttribute("usernameError", "Пользователь уже существует");
            model.addAttribute("key", client_secret);
            return "registration";
        }
        return "redirect:/login";
    }

    @GetMapping("/activate/{code}")
    public String activate(@PathVariable String code, Model model) {
        boolean isActivated = userService.activateUser(code);

        if (isActivated) {
            model.addAttribute("messageType", "success");
            model.addAttribute("message", "Пользователь был успешно активирован");
        } else {
            model.addAttribute("messageType", "danger");
            model.addAttribute("message", "Активационный код не действителен");
        }
        return "login";
    }

    @GetMapping("users/profile")
    public String getProfile(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("username", user.getUsername());
        model.addAttribute("email", user.getEmail());

        return "profile";
    }

    @PostMapping("users/profile")
    public String updateProfile(
            @AuthenticationPrincipal User user,
            @RequestParam String password,
            @RequestParam String email,
            Model model
    ) {
        boolean res = userService.updateProfile(user, password, email);
        if (res) {
            model.addAttribute("username", user.getUsername());
            model.addAttribute("email", user.getEmail());
            model.addAttribute("message", "Профиль был изменен");
            return "profile";
        }
        return "redirect:/users/profile";
    }

}
