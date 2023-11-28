package com.mkh.tutoringplatform.web.validaton;


import com.mkh.tutoringplatform.domain.user.user.User;
import com.mkh.tutoringplatform.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
@AllArgsConstructor
public class UserValidator implements Validator {
    private final UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;
        Optional<User> optionalUserByEmail = userService.getByEmail(user.getEmail());
        Optional<User> optionalUserByUsername = userService.getByUsername(user.getUsername());
        if (optionalUserByEmail.isPresent())
            errors.rejectValue("email","","this email is used");
        if (optionalUserByUsername.isPresent())
            errors.rejectValue("username", "", "this username is used");
    }
}
