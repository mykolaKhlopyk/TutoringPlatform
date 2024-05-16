package com.mkh.tutoringplatform.service.impl;

import com.mkh.tutoringplatform.domain.exception.ResourceNotFoundException;
import com.mkh.tutoringplatform.domain.user.user.User;
import com.mkh.tutoringplatform.security.UserDetailsImpl;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CommonUserDetailsService implements UserDetailsService {

    private final UserServiceImpl userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.getByEmail(email).orElseGet(
                () -> userService.getByUsername(email).orElseThrow(ResourceNotFoundException::new)
        );
        return new UserDetailsImpl(user);
    }
}
