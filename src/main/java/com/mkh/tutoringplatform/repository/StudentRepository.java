package com.mkh.tutoringplatform.repository;

import com.mkh.tutoringplatform.domain.user.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository {

    Optional<Student> findById(long id);

    void save(Student student);
}
