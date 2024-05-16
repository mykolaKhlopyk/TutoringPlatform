package com.mkh.tutoringplatform.service.impl;

import com.mkh.tutoringplatform.domain.user.Course;
import com.mkh.tutoringplatform.repository.CourseRepository;
import com.mkh.tutoringplatform.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
    public void saveCourse(String courseName, String courseDescription, long teacherId) {
        var course = Course.builder()
                .name(courseName)
                .description(courseDescription)
                .teacherId(teacherId)
                .build();
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

    @Override
    public void sendRequestForSubscribeStudentToCourse(long studentId, long courseId) {
        courseRepository.sendRequestForSubscribeStudentToCourse(studentId, courseId);
    }

    @Override
    public void agreeRequestForSubscribeStudentToCourse(long courseId, long studentId) {
        courseRepository.agreeRequestForSubscribeStudentToCourse(courseId, studentId);
    }

    @Override
    public void disagreeRequestForSubscribeStudentToCourse(long courseId, long studentId) {
        courseRepository.disagreeRequestForSubscribeStudentToCourse(courseId, studentId);
    }
}
