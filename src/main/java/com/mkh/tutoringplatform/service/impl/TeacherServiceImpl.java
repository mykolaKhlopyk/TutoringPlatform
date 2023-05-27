package com.mkh.tutoringplatform.service.impl;

import com.mkh.tutoringplatform.domain.user.student.Student;
import com.mkh.tutoringplatform.domain.user.teacher.Teacher;
import com.mkh.tutoringplatform.repository.StudentRepository;
import com.mkh.tutoringplatform.repository.TeacherRepository;
import com.mkh.tutoringplatform.service.TeacherService;
import lombok.AllArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;

    @Override
    public List<Teacher> getAll() {
        return teacherRepository.findAll();
    }

    @Override
    public List<Teacher> getAllNewForStudent(Student student) {
        List<Teacher> newForStudentTeachers =  teacherRepository.findAll();
        student = studentRepository.findById(Long.valueOf(student.getId())).get();
        Hibernate.initialize(student.getTeachers());
        newForStudentTeachers.removeAll(student.getTeachers());
        return newForStudentTeachers;
    }
}
