package com.mkh.tutoringplatform.service;

import com.mkh.tutoringplatform.domain.user.Student;

public interface StudentService {

    Student findById(long studentId);

    void joinStudentToCourse(long studentId, long courseId);

    void leaveStudentFromCourse(long studentId, long courseId);
}
