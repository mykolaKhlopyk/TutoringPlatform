package com.mkh.tutoringplatform.repository;

import com.mkh.tutoringplatform.domain.user.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository {

    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);

    User getOne(Long aLong);

    void save(User user);

    List<User> findAll();

    Optional<User> findById(long id);
}
