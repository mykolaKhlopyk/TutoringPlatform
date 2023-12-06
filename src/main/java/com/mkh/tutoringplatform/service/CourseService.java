package com.mkh.tutoringplatform.service;

import com.mkh.tutoringplatform.domain.user.student.Course;

import java.util.List;

public interface CourseService {

    List<Course> getAllCourses();

    Course getCourseById(long id);

    Course updateCourse(long courseId, String literature, String task, String link);

    List<Course> getTeacherCourses(long id);

    boolean isCourseWithNameExist(String name);

    void saveCourse(long id, Course course);

    void deleteCourse(long courseId);
}
