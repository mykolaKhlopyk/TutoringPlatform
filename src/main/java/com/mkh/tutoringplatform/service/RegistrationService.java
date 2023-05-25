package com.mkh.tutoringplatform.service;

import com.mkh.tutoringplatform.domain.user.student.Student;
import com.mkh.tutoringplatform.domain.user.teacher.Teacher;
import com.mkh.tutoringplatform.domain.user.user.User;

public interface RegistrationService {
    void registerUser(User user);
    void registerTeacher(Teacher teacher);
    void registerStudent(Student student);
}
