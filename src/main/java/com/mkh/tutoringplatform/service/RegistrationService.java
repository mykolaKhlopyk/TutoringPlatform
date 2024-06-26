package com.mkh.tutoringplatform.service;

import com.mkh.tutoringplatform.domain.user.Student;
import com.mkh.tutoringplatform.domain.user.Teacher;
import com.mkh.tutoringplatform.domain.user.user.User;

public interface RegistrationService {

    void registerUser(User user);

    long registerTeacher(Teacher teacher);

    long registerStudent(Student student);
}
