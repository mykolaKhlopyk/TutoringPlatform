package com.mkh.tutoringplatform.repository;

import com.mkh.tutoringplatform.domain.user.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository {

    Student getOne(long studentId);

    Optional<Student> findById(long id);

    void save(Student student);
}
