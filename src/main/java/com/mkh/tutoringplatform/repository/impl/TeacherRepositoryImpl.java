package com.mkh.tutoringplatform.repository.impl;

import com.mkh.tutoringplatform.domain.user.teacher.Teacher;
import com.mkh.tutoringplatform.repository.TeacherRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TeacherRepositoryImpl implements TeacherRepository {

    private final JdbcTemplate jdbcTemplate;

    public TeacherRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Teacher getOne(long id) {
        return null;
    }

    @Override
    public void save(Teacher teacher) {

    }

    @Override
    public Teacher findById(long id) {
        return null;
    }

    @Override
    public List<Teacher> findAll() {
        return null;
    }
}
