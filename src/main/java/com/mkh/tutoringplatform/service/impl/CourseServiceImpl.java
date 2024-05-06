package com.mkh.tutoringplatform.service.impl;

import com.mkh.tutoringplatform.domain.user.student.Course;
import com.mkh.tutoringplatform.domain.user.teacher.Teacher;
import com.mkh.tutoringplatform.repository.CourseRepository;
import com.mkh.tutoringplatform.repository.TeacherRepository;
import com.mkh.tutoringplatform.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    private final TeacherRepository teacherRepository;

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course getCourseById(long id) {
        return courseRepository.findById(id);
    }

    @Override
    public Course updateCourse(long courseId, String literature, String task, String link) {
        Course course = courseRepository.getOne(courseId);
        course.setLiterature(literature);
        course.setTasks(task);
        course.setLinks(link);
        return courseRepository.save(course);
    }

    @Override
    public List<Course> getTeacherCourses(long id) {
        Teacher teacher = teacherRepository.getOne(id);
        Hibernate.initialize(teacher.getCourses());
        return teacher.getCourses();
    }

    @Override
    public boolean isCourseWithNameExist(String name) {
        return courseRepository.existsCourseByName(name);
    }

    @Override
    @Transactional
    public void saveCourse(long id, Course course) {
        Teacher teacher = teacherRepository.getOne(id);
        course.setTeacher(teacher);
        teacher.getCourses().add(course);
        teacherRepository.save(teacher);
        courseRepository.save(course);
    }

    @Override
    public void deleteCourse(long courseId) {
        courseRepository.deleteById(courseId);
    }
}
