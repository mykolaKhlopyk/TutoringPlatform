package com.mkh.tutoringplatform.service;

import com.mkh.tutoringplatform.domain.user.student.Student;
import com.mkh.tutoringplatform.domain.user.teacher.Teacher;

import java.util.List;

public interface TeacherService {
    List<Teacher> getAll();
    List<Teacher> getAllNewForStudent(Student student);
}
