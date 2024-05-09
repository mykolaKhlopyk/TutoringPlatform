package com.mkh.tutoringplatform.repository.jpa;

import com.mkh.tutoringplatform.repository.entity.SqlUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaUserRepository extends JpaRepository<SqlUser, Long>{

    Optional<SqlUser> findByEmail(String email);

    Optional<SqlUser> findByUsername(String username);
}
