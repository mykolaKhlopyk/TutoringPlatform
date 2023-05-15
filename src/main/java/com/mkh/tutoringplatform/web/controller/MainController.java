package com.mkh.tutoringplatform.web.controller;

import com.mkh.tutoringplatform.domain.user.user.User;
import com.mkh.tutoringplatform.security.UserDetailsImpl;
import com.mkh.tutoringplatform.service.impl.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
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
public class MainController {

    private final UserService userService;

    @GetMapping("/hello")
    public String sayHello(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        System.out.println(userDetails.getUser().getName());
        return "hello";
    }

    @GetMapping("/new-user")
    public String createUser(@ModelAttribute("user")User user){
        return "new-user";
    }

    @PostMapping("/new-user")
    public String create(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return "/new-user";
        }
        userService.save(user);
        return "redirect:/index";
    }


    @GetMapping("/index")
    public String showAll(Model model){
        model.addAttribute("users", userService.getAll());
        return "index";
    }
}
