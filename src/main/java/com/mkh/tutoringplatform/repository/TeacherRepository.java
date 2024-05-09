package com.mkh.tutoringplatform.repository;

import com.mkh.tutoringplatform.domain.user.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TeacherRepository {

    void save(Teacher teacher);

    Optional<Teacher> findById(long id);

    List<Teacher> findAll();
}
