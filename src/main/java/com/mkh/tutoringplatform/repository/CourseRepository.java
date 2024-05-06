package com.mkh.tutoringplatform.repository;

import com.mkh.tutoringplatform.domain.user.student.Course;

import java.util.List;

public interface CourseRepository {

    boolean existsCourseByName(String name);

    List<Course> findAll();

    Course findById(long id);

    Course save(Course course);

    Course getOne(long courseId);

    void deleteById(long courseId);
}
