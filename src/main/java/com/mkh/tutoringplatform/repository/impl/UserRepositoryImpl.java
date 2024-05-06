package com.mkh.tutoringplatform.repository.impl;

import com.mkh.tutoringplatform.domain.user.user.User;
import com.mkh.tutoringplatform.repository.UserRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return Optional.empty();
    }

    @Override
    public User getOne(Long aLong) {
        return null;
    }

    @Override
    public void save(User user) {
        // TODO document why this method is empty
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public Optional<User> findById(long id) {
        return Optional.empty();
    }
}
