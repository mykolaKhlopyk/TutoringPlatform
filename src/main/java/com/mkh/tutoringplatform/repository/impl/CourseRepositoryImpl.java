package com.mkh.tutoringplatform.repository.impl;

import com.mkh.tutoringplatform.domain.user.Course;
import com.mkh.tutoringplatform.repository.CourseRepository;
import com.mkh.tutoringplatform.repository.jpa.JpaCourseRepository;
import com.mkh.tutoringplatform.repository.mapper.CourseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CourseRepositoryImpl implements CourseRepository {

    private final JpaCourseRepository jpaCourseRepository;

    public CourseRepositoryImpl(JpaCourseRepository jpaCourseRepository) {
        this.jpaCourseRepository = jpaCourseRepository;
    }

    @Override
    public boolean existsCourseByName(String name) {
        return jpaCourseRepository.existsCourseByName(name);
    }

    @Override
    public List<Course> findAll() {
        return jpaCourseRepository.findAll().stream().map(CourseMapper::mapToDomainModel).toList();
    }

    @Override
    public Optional<Course> findById(long id) {
        return jpaCourseRepository.findById(id).map(CourseMapper::mapToDomainModel);
    }

    @Override
    public void save(Course course) {
        jpaCourseRepository.save(CourseMapper.mapToSqlModelWithoutDependencies(course));
    }

    @Override
    public void deleteById(long id) {
        jpaCourseRepository.deleteById(id);
    }
}
