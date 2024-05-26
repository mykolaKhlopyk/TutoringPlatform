package com.mkh.tutoringplatform.repository.impl;

import com.mkh.tutoringplatform.domain.user.Lesson;
import com.mkh.tutoringplatform.repository.LessonRepository;
import com.mkh.tutoringplatform.repository.entity.SqlGroup;
import com.mkh.tutoringplatform.repository.jpa.JpaGroupRepository;
import com.mkh.tutoringplatform.repository.jpa.JpaLessonRepository;
import com.mkh.tutoringplatform.repository.jpa.JpaTeacherRepository;
import com.mkh.tutoringplatform.repository.mapper.LessonMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class LessonRepositoryImpl implements LessonRepository {

    private final JpaLessonRepository jpaLessonRepository;

    private final JpaGroupRepository jpaGroupRepository;

    private final JpaTeacherRepository jpaTeacherRepository;

    @Override
    public void deleteById(long id) {
        jpaLessonRepository.deleteById(id);
    }

    @Override
    public Optional<Lesson> findById(long id) {
        return jpaLessonRepository.findById(id).map(LessonMapper::mapToDomainModel);
    }

    @Override
    public Lesson save(Lesson lesson) {
        var sqlLesson = LessonMapper.mapToSqlModelWithoutDependencies(lesson);
        var group = jpaGroupRepository.getReferenceById(lesson.getGroupId());
        sqlLesson.setGroup(group);
        return LessonMapper.mapToDomainModel(jpaLessonRepository.save(sqlLesson));
    }

    @Override
    public List<Lesson> getTeacherLessons(long teacherId) {
        var teacher = jpaTeacherRepository.getReferenceById(teacherId);
        //maybe incorrect because of lazy hibernate
        return teacher.getCourses().stream()
                .flatMap(course -> course.getGroups().stream())
                .map(SqlGroup::getLessons)
                .flatMap(Collection::stream)
                .map(LessonMapper::mapToDomainModel)
                .toList();
    }

    @Override
    public List<Lesson> getLessonsFromGroups(List<Long> groupsIds) {
        return jpaGroupRepository.findAllById(groupsIds).stream()
                .map(SqlGroup::getLessons)
                .flatMap(Collection::stream)
                .map(LessonMapper::mapToDomainModel)
                .toList();
    }
}
