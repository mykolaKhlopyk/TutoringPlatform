package com.mkh.tutoringplatform.service.impl;

import com.mkh.tutoringplatform.domain.exception.ResourceNotFoundException;
import com.mkh.tutoringplatform.domain.user.user.Role;
import com.mkh.tutoringplatform.domain.user.user.User;
import com.mkh.tutoringplatform.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public User getById(long id){
        return userRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    @Transactional
    public void save(User user){
        if (user.getRoles() == null || user.getRoles().isEmpty())
            user.setRoles(new HashSet<>(List.of(Role.ROLE_STUDENT)));
        userRepository.save(user);
    }

    @Transactional
    public void update(long id, User updatedUser){
        updatedUser.setId(id);
        userRepository.save(updatedUser);
    }

    public User getByEmail(String email) {
        System.out.println(userRepository.findByEmail(email).orElseThrow(ResourceNotFoundException::new));
        return userRepository.findByEmail(email).orElseThrow(ResourceNotFoundException::new);
    }
}
