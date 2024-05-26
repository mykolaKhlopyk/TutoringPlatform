package com.mkh.tutoringplatform.repository.impl;

import com.mkh.tutoringplatform.domain.user.Teacher;
import com.mkh.tutoringplatform.repository.TeacherRepository;
import com.mkh.tutoringplatform.repository.jpa.JpaCourseRepository;
import com.mkh.tutoringplatform.repository.jpa.JpaTeacherRepository;
import com.mkh.tutoringplatform.repository.mapper.TeacherMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TeacherRepositoryImpl implements TeacherRepository {

    private final JpaTeacherRepository jpaTeacherRepository;

    private final JpaCourseRepository jpaCourseRepository;

    @Override
    public long save(Teacher teacher) {
        return jpaTeacherRepository.save(TeacherMapper.mapToSqlModelWithoutDependencies(teacher)).getId();
    }

    @Override
    public Optional<Teacher> findById(long id) {
        return jpaTeacherRepository.findById(id).map(TeacherMapper::mapToDomainModel);
    }

    @Override
    public List<Teacher> findAll() {
        return jpaTeacherRepository.findAll().stream().map(TeacherMapper::mapToDomainModel).toList();
    }

    @Override
    public long getCourseOwnerId(long courseId) {
        return jpaCourseRepository.getReferenceById(courseId).getTeacher().getId();
    }
}
