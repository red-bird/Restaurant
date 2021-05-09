package com.redbird.restaurant.controllers.pub;

import com.redbird.restaurant.models.User;
import com.redbird.restaurant.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Map;

@Controller
@RequestMapping
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(
            @RequestParam("passwordConfirm") String passwordConfirm,
            @Valid User user,
            BindingResult bindingResult,
            Model model
    ) {
        boolean isConfirmEmpty = !StringUtils.hasText(passwordConfirm);
        if (isConfirmEmpty) {
            model.addAttribute("passwordConfirmError", "Подтверждение пароля не может быть пустыми");
        }

        if (user.getPassword() != null && passwordConfirm != null
                && !user.getPassword().equals(passwordConfirm)) {
            model.addAttribute("passwordError", "Пароли различаются");
            return "registration";
        }

        if (isConfirmEmpty || bindingResult.hasErrors()) {
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);

            model.mergeAttributes(errors);

            return "registration";
        }

        user = userService.saveUser(user);
        if (user == null) {
            model.addAttribute("usernameError", "Пользователь уже существует");
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
