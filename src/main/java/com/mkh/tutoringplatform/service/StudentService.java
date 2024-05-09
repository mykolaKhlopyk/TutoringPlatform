package com.mkh.tutoringplatform.service;

import com.mkh.tutoringplatform.domain.user.Course;
import com.mkh.tutoringplatform.domain.user.Student;
import com.mkh.tutoringplatform.domain.user.Teacher;

import java.util.List;

public interface StudentService {

    Student findById(long studentId);

    void joinStudentToCourse(long studentId, long courseId);

    void leaveStudentFromCourse(long studentId, long courseId);
}
