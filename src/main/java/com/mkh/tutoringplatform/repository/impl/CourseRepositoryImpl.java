package com.mkh.tutoringplatform.repository.impl;

import com.mkh.tutoringplatform.domain.user.student.Course;
import com.mkh.tutoringplatform.repository.CourseRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CourseRepositoryImpl implements CourseRepository {

    private final JdbcTemplate jdbcTemplate;

    public CourseRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean existsCourseByName(String name) {
        return false;
    }

    @Override
    public List<Course> findAll() {
        return null;
    }

    @Override
    public Course findById(long id) {
        return null;
    }

    @Override
    public Course save(Course course) {
        return null;
    }

    @Override
    public Course getOne(long courseId) {
        return null;
    }

    @Override
    public void deleteById(long courseId) {

    }
}
