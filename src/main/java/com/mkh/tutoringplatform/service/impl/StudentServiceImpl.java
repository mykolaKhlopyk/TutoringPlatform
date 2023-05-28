package com.mkh.tutoringplatform.service.impl;

import com.mkh.tutoringplatform.domain.user.student.Student;
import com.mkh.tutoringplatform.domain.user.teacher.Teacher;
import com.mkh.tutoringplatform.repository.StudentRepository;
import com.mkh.tutoringplatform.repository.TeacherRepository;
import com.mkh.tutoringplatform.service.StudentService;
import lombok.AllArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class StudentServiceImpl implements StudentService {
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;
    @Override
    @Transactional
    public void requestTeacher(long id, Student authenticatedStudent) {
        Teacher teacher = teacherRepository.findById(id).get();
        authenticatedStudent = studentRepository.findById(authenticatedStudent.getId()).get();
        teacher.getNotConfirmedStudents().add(authenticatedStudent);
        teacherRepository.save(teacher);
    }
}
