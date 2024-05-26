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

    void saveCourse(String courseName, String courseDescription, long teacherId);

    void deleteCourse(long courseId);

    void sendRequestForSubscribeStudentToCourse(long studentId, long courseId);

    void agreeRequestForSubscribeStudentToCourse(long courseId, long studentId);

    void disagreeRequestForSubscribeStudentToCourse(long courseId, long studentId);

    void withdrawStudentFromCourse(long courseId, long studentId);
}
