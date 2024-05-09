package com.mkh.tutoringplatform.repository;

import com.mkh.tutoringplatform.domain.user.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CourseRepository {

    boolean existsCourseByName(String name);

    List<Course> findAll();

    Optional<Course> findById(long id);

    void save(Course course);

    void deleteById(long id);

    Course updateCourse(long id, String literature, String task, String link);

    List<Course> getTeacherCourses(long teacherId);

    List<Course> getStudentCourses(long studentId);
}