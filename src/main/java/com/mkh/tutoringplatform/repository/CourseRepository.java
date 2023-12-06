package com.mkh.tutoringplatform.repository;

import com.mkh.tutoringplatform.domain.user.student.Course;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
    boolean existsCourseByName(String name);
}
