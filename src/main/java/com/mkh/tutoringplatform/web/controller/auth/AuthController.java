package com.mkh.tutoringplatform.web.controller.auth;

import com.mkh.tutoringplatform.domain.user.user.Role;
import com.mkh.tutoringplatform.domain.user.user.User;
import com.mkh.tutoringplatform.service.RegistrationService;
import com.mkh.tutoringplatform.web.validaton.UserValidator;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final UserValidator userValidator;
    private final RegistrationService registrationService;

    @GetMapping("/login")
    public String loginPage(){
        return "auth/login";
    }

    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("user") User user, @ModelAttribute("role") String role){
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String performRegistration( @ModelAttribute("role") String role, @ModelAttribute("user") @Valid User user, BindingResult bindingResult){
        if (role.equals("teacher"))
            user.setRoles(new HashSet<>(List.of(Role.ROLE_TEACHER)));
        else
            user.setRoles(new HashSet<>(List.of(Role.ROLE_STUDENT)));

        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors())
            return "/auth/registration";
        registrationService.register(user);
        return "redirect:/auth/login";
    }

}
