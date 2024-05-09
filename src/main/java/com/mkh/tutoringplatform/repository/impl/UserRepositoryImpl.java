package com.mkh.tutoringplatform.repository.impl;

import com.mkh.tutoringplatform.domain.user.user.User;
import com.mkh.tutoringplatform.repository.UserRepository;
import com.mkh.tutoringplatform.repository.jpa.JpaUserRepository;
import com.mkh.tutoringplatform.repository.mapper.UserMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final JpaUserRepository jpaUserRepository;

    public UserRepositoryImpl(JpaUserRepository jpaUserRepository) {
        this.jpaUserRepository = jpaUserRepository;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return jpaUserRepository.findByEmail(email).map(UserMapper::mapToDomainModel);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return jpaUserRepository.findByUsername(username).map(UserMapper::mapToDomainModel);
    }

    @Override
    public Optional<User> findById(long id) {
        return jpaUserRepository.findById(id).map(UserMapper::mapToDomainModel);
    }

    @Override
    public void save(User user) {
        jpaUserRepository.save(UserMapper.mapToSqlModelWithoutDependencies(user));
    }

    @Override
    public List<User> findAll() {
        return jpaUserRepository.findAll().stream().map(UserMapper::mapToDomainModel).toList();
    }
}