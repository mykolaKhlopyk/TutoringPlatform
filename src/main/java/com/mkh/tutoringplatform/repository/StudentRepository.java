package com.mkh.tutoringplatform.repository;

import com.mkh.tutoringplatform.domain.user.Student;

import java.util.Optional;

public interface StudentRepository {

    Optional<Student> findById(long id);

    void save(Student student);

    void joinStudentToCourse(long studentId, long courseId);

    void leaveStudentFromCourse(long studentId, long courseId);
}
