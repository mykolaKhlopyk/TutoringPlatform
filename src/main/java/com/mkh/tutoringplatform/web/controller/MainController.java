package com.mkh.tutoringplatform.web.controller;

import com.mkh.tutoringplatform.domain.user.user.Role;
import com.mkh.tutoringplatform.domain.user.user.User;
import com.mkh.tutoringplatform.security.UserDetailsImpl;
import com.mkh.tutoringplatform.service.impl.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@AllArgsConstructor
@RequestMapping("/home")
public class MainController {

    private final UserServiceImpl userService;

    @GetMapping()
    public String redirectToTeacherOrStudentPage(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        User user = userDetails.getUser();
        if (user.getRoles().contains(Role.ROLE_STUDENT))
            return "redirect:/student/";
        return "redirect:/teacher/";
    }




//    @GetMapping("/index")
//    public String showAll(Model model){
//        model.addAttribute("users", userService.getAll());
//        return "index";
//    }
}
