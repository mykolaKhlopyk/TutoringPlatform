package com.mkh.tutoringplatform.service;

import com.mkh.tutoringplatform.domain.user.Course;

import java.util.List;

public interface CourseService {

    List<Course> getAllCourses();

    Course getCourseById(long id);

    Course updateCourse(long courseId, String literature, String task, String link);

    List<Course> getTeacherCourses(long teacherId);

    List<Course> getStudentCourses(long studentId);

    boolean isCourseWithNameExist(String name);

    void saveCourse(Course course);

    void deleteCourse(long courseId);
}
