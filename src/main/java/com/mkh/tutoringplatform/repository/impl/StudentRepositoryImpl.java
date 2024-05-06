package com.mkh.tutoringplatform.repository.impl;

import com.mkh.tutoringplatform.domain.user.student.Student;
import com.mkh.tutoringplatform.repository.StudentRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class StudentRepositoryImpl implements StudentRepository {

    private final JdbcTemplate jdbcTemplate;

    public StudentRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Student getOne(long studentId) {
        return null;
    }

    @Override
    public Optional<Student> findById(long id) {
        return null;
    }

    @Override
    public void save(Student student) {
        // TODO document why this method is empty
    }
}
