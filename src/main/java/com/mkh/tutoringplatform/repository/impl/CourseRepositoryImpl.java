package com.mkh.tutoringplatform.repository.impl;

import com.mkh.tutoringplatform.domain.user.Course;
import com.mkh.tutoringplatform.repository.CourseRepository;
import com.mkh.tutoringplatform.repository.entity.SqlCourse;
import com.mkh.tutoringplatform.repository.jpa.JpaCourseRepository;
import com.mkh.tutoringplatform.repository.jpa.JpaTeacherRepository;
import com.mkh.tutoringplatform.repository.mapper.CourseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CourseRepositoryImpl implements CourseRepository {

    private final JpaCourseRepository jpaCourseRepository;

    private final JpaTeacherRepository jpaTeacherRepository;

    private final JpaTeacherRepository jpaStudentRepository;

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
        var teacher = jpaTeacherRepository.getReferenceById(course.getId());
        var sqlCourse = jpaCourseRepository.save(CourseMapper.mapToSqlModelWithoutDependencies(course));
        teacher.getCourses().add(sqlCourse);
//        jpaCourseRepository.save(sqlCourse);
    }

    @Override
    public void deleteById(long id) {
        jpaCourseRepository.deleteById(id);
    }

    @Override
    public Course updateCourse(long id, String literature, String task, String link) {
        SqlCourse course = jpaCourseRepository.getReferenceById(id);
        course.setLiterature(literature);
        course.setTasks(task);
        course.setLinks(link);
        return CourseMapper.mapToDomainModel(jpaCourseRepository.save(course));
    }

    @Override
    public List<Course> getTeacherCourses(long teacherId) {
        return jpaTeacherRepository.getReferenceById(teacherId)
                .getCourses().stream()
                .map(CourseMapper::mapToDomainModel)
                .toList();
    }

    @Override
    public List<Course> getStudentCourses(long studentId) {
        return jpaStudentRepository.getReferenceById(studentId)
                .getCourses().stream()
                .map(CourseMapper::mapToDomainModel)
                .toList();
    }
}
