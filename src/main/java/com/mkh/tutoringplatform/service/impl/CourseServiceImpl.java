package com.mkh.tutoringplatform.service.impl;

import com.mkh.tutoringplatform.domain.user.Course;
import com.mkh.tutoringplatform.domain.user.Teacher;
import com.mkh.tutoringplatform.repository.CourseRepository;
import com.mkh.tutoringplatform.repository.TeacherRepository;
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

    private final CourseRepository courseRepository;

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course getCourseById(long id) {
        return courseRepository.findById(id).get();
    }

    @Override
    public Course updateCourse(long courseId, String literature, String task, String link) {
        return courseRepository.updateCourse(courseId, literature, task, link);
    }

    @Override
    public List<Course> getTeacherCourses(long teacherId) {
        return courseRepository.getTeacherCourses(teacherId);
    }

    @Override
    public boolean isCourseWithNameExist(String name) {
        return courseRepository.existsCourseByName(name);
    }

    @Override
    public void saveCourse(Course course) {
        courseRepository.save(course);
    }

    @Override
    public void deleteCourse(long courseId) {
        courseRepository.deleteById(courseId);
    }

    @Override
    public List<Course> getStudentCourses(long studentId) {
        return courseRepository.getStudentCourses(studentId);
    }
}
