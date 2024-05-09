package com.mkh.tutoringplatform.repository.impl;

import com.mkh.tutoringplatform.domain.user.Lesson;
import com.mkh.tutoringplatform.repository.LessonRepository;
import com.mkh.tutoringplatform.repository.jpa.JpaLessonRepository;
import com.mkh.tutoringplatform.repository.mapper.LessonMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class LessonRepositoryImpl implements LessonRepository {

    private final JpaLessonRepository jpaLessonRepository;

    public LessonRepositoryImpl(JpaLessonRepository jpaLessonRepository) {
        this.jpaLessonRepository = jpaLessonRepository;
    }

    @Override
    public void deleteById(long id) {
        jpaLessonRepository.deleteById(id);
    }

    @Override
    public Optional<Lesson> findById(long id) {
        return jpaLessonRepository.findById(id).map(LessonMapper::mapToDomainModel);
    }

    @Override
    public void save(Lesson lesson) {
        jpaLessonRepository.save(LessonMapper.mapToSqlModelWithoutDependencies(lesson));
    }
}
