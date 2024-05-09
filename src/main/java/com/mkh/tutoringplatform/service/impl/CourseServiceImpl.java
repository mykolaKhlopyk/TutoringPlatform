package com.mkh.tutoringplatform.service.impl;

import com.mkh.tutoringplatform.domain.user.Course;
import com.mkh.tutoringplatform.domain.user.Teacher;
import com.mkh.tutoringplatform.repository.jpa.JpaCourseRepository;
import com.mkh.tutoringplatform.repository.jpa.JpaTeacherRepository;
import com.mkh.tutoringplatform.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final JpaCourseRepository jpaCourseRepository;

    private final JpaTeacherRepository jpaTeacherRepository;

    @Override
    public List<Course> getAllCourses() {
        return jpaCourseRepository.findAll();
    }

    @Override
    public Course getCourseById(long id) {
        return jpaCourseRepository.findById(id).get();
    }

    @Override
    public Course updateCourse(long courseId, String literature, String task, String link) {
        Course course = jpaCourseRepository.getOne(courseId);
        course.setLiterature(literature);
        course.setTasks(task);
        course.setLinks(link);
        return jpaCourseRepository.save(course);
    }

    @Override
    public List<Course> getTeacherCourses(long id) {
        Teacher teacher = jpaTeacherRepository.getOne(id);
        Hibernate.initialize(teacher.getCourses());
        return teacher.getCourses();
    }

    @Override
    public boolean isCourseWithNameExist(String name) {
        return jpaCourseRepository.existsCourseByName(name);
    }

    @Override
    @Transactional
    public void saveCourse(long id, Course course) {
        Teacher teacher = jpaTeacherRepository.getOne(id);
        course.setTeacher(teacher);
        teacher.getCourses().add(course);
        jpaTeacherRepository.save(teacher);
        jpaCourseRepository.save(course);
    }

    @Override
    public void deleteCourse(long courseId) {
        jpaCourseRepository.deleteById(courseId);
    }
}
