package com.mkh.tutoringplatform.service.impl;

import com.mkh.tutoringplatform.domain.user.Student;
import com.mkh.tutoringplatform.domain.user.Teacher;
import com.mkh.tutoringplatform.domain.user.user.User;
import com.mkh.tutoringplatform.repository.jpa.JpaStudentRepository;
import com.mkh.tutoringplatform.repository.jpa.JpaTeacherRepository;
import com.mkh.tutoringplatform.repository.jpa.JpaUserRepository;
import com.mkh.tutoringplatform.service.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
@AllArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

    private final JpaUserRepository jpaUserRepository;

    private final JpaTeacherRepository jpaTeacherRepository;

    private final JpaStudentRepository jpaStudentRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setPassword(user.getPassword());
        jpaUserRepository.save(user);
    }

    @Override
    public void registerTeacher(Teacher teacher) {
        teacher.setRegisteredAt(new Date());
        jpaTeacherRepository.save(teacher);
    }

    @Override
    public void registerStudent(Student student) {
        jpaStudentRepository.save(student);
    }
}
