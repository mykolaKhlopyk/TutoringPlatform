package com.mkh.tutoringplatform.web.controller.auth;

import com.mkh.tutoringplatform.domain.user.student.Grade;
import com.mkh.tutoringplatform.domain.user.student.Student;
import com.mkh.tutoringplatform.domain.user.teacher.Subject;
import com.mkh.tutoringplatform.domain.user.teacher.Teacher;
import com.mkh.tutoringplatform.domain.user.user.Role;
import com.mkh.tutoringplatform.domain.user.user.User;
import com.mkh.tutoringplatform.service.RegistrationService;
import com.mkh.tutoringplatform.web.validaton.UserValidator;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.dom4j.rule.Mode;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import javax.validation.Valid;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final UserValidator userValidator;
    private final RegistrationService registrationService;

    private User createdUser;

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
        createdUser = user;
        if (role.equals("teacher")) {
            user.setRoles(new HashSet<>(List.of(Role.ROLE_TEACHER)));
            return "redirect:/auth/registration/teacher";
        } else {
            user.setRoles(new HashSet<>(List.of(Role.ROLE_STUDENT)));
            return "redirect:/auth/registration/student";
        }
    }

    @GetMapping("/registration/teacher")
    public String registrationPage(@ModelAttribute("teacher") Teacher teacher, Model model) {
        model.addAttribute("subjects", Subject.values());
        model.addAttribute("grades", Grade.values());
        return "auth/teacher-registration";
    }

    @PostMapping("/registration/teacher")
    public String performRegistration(@ModelAttribute("teacher") Teacher teacher) {
        System.out.println(teacher.getGrades());
        System.out.println(teacher.getSubjects());
        teacher.setUser(createdUser);
        registrationService.registerUser(createdUser);
        registrationService.registerTeacher(teacher);
        createdUser = null;
        return "redirect:/auth/login";
    }

    @GetMapping("/registration/student")
    public String registrationPage(@ModelAttribute("student") Student student, Model model) {
        model.addAttribute("grades", Grade.values());
        return "auth/student-registration";
    }

    @PostMapping("/registration/student")
    public String performRegistration(@ModelAttribute("student") Student student) {
        student.setUser(createdUser);
        registrationService.registerUser(createdUser);
        registrationService.registerStudent(student);
        createdUser = null;
        return "redirect:/auth/login";
    }
}
