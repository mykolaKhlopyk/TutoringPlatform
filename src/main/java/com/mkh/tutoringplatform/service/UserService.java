package com.mkh.tutoringplatform.service;

import com.mkh.tutoringplatform.domain.user.user.User;


import java.util.HashSet;
import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAll();
    public User getById(long id);
    void save(User user);
    public void update(long id, User updatedUser);
    Optional<User> getByEmail(String email);
    Optional<User> getByUsername(String username);
}
