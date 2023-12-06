package com.mkh.tutoringplatform.web.controller.auth;

import com.mkh.tutoringplatform.domain.user.student.Student;
import com.mkh.tutoringplatform.domain.user.teacher.Teacher;
import com.mkh.tutoringplatform.domain.user.user.Role;
import com.mkh.tutoringplatform.domain.user.user.User;
import com.mkh.tutoringplatform.service.RegistrationService;
import com.mkh.tutoringplatform.web.validaton.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final UserValidator userValidator;

    private final RegistrationService registrationService;

    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }

    @GetMapping("/registration/user")
    public String registrationPage(@ModelAttribute("user") User user, @ModelAttribute("role") String role) {
        return "auth/user-registration";
    }

    @PostMapping("/registration/user")
    public String performRegistration(@ModelAttribute("role") String role, @ModelAttribute("user") @Valid User user, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return "auth/user-registration";
        }
        registrationService.registerUser(user);
        if (role.equals("teacher")) {
            user.setRoles(new HashSet<>(List.of(Role.ROLE_TEACHER)));
            Teacher teacher = new Teacher();
            teacher.setUser(user);
            registrationService.registerTeacher(teacher);
        } else {
            user.setRoles(new HashSet<>(List.of(Role.ROLE_STUDENT)));
            Student student = new Student();
            student.setUser(user);
            registrationService.registerStudent(student);
        }
        return "redirect:/auth/login";
    }
}
