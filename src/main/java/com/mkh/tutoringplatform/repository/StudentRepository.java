package com.mkh.tutoringplatform.repository;

import com.mkh.tutoringplatform.domain.user.Student;

import java.util.List;
import java.util.Optional;

public interface StudentRepository {

    Optional<Student> findById(long id);

    long save(Student student);

    void joinStudentToCourse(long studentId, long courseId);

    void leaveStudentFromCourse(long studentId, long courseId);

    List<Student> getAllStudentsAskedForSubscribeForCourse(long courseId);

    List<Student> getAllStudentsFromCourse(long courseId);
}
