package com.mkh.tutoringplatform.service;

import com.mkh.tutoringplatform.domain.user.Course;
import com.mkh.tutoringplatform.domain.user.Student;
import com.mkh.tutoringplatform.domain.user.Teacher;

import java.util.List;

public interface StudentService {

    void requestTeacher(long id, Student authenticatedStudent);

    List<Teacher> getAllRequestedByStudent(Student student);

    void cancelRequest(long id, Student authenticatedStudent);

    void deleteTeacher(long student_id, long teacher_id);

    List<Course> getStudentCourses(long id);

    void joinStudentToCourse(long studentId, long courseId);

    void leaveCourse(long id, long courseId);
}
