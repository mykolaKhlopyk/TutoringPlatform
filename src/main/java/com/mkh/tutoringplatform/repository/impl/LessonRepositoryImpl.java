package com.mkh.tutoringplatform.repository.impl;

import com.mkh.tutoringplatform.domain.user.student.Lesson;
import com.mkh.tutoringplatform.repository.LessonRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LessonRepositoryImpl implements LessonRepository {

    private final JdbcTemplate jdbcTemplate;

    public LessonRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void delete(Lesson lesson) {
        // TODO document why this method is empty
    }

    @Override
    public Lesson getOne(long lessonId) {
        return null;
    }

    @Override
    public void save(Lesson lesson) {

    }
}
