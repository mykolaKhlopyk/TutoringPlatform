package com.mkh.tutoringplatform.repository;

import com.mkh.tutoringplatform.domain.user.Teacher;

import java.util.List;
import java.util.Optional;

public interface TeacherRepository {

    long save(Teacher teacher);

    Optional<Teacher> findById(long id);

    List<Teacher> findAll();

    long getCourseOwnerId(long courseId);
}
