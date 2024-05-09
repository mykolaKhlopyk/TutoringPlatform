package com.mkh.tutoringplatform.service.impl;

import com.mkh.tutoringplatform.domain.user.Student;
import com.mkh.tutoringplatform.domain.user.Teacher;
import com.mkh.tutoringplatform.repository.jpa.JpaStudentRepository;
import com.mkh.tutoringplatform.repository.jpa.JpaTeacherRepository;
import com.mkh.tutoringplatform.service.TeacherService;
import lombok.AllArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class TeacherServiceImpl implements TeacherService {

    private final JpaTeacherRepository jpaTeacherRepository;

    private final JpaStudentRepository jpaStudentRepository;

    @Override
    public Teacher getById(long id) {
        return jpaTeacherRepository.findById(id).get();
    }

    @Override
    public List<Teacher> getAll() {
        return jpaTeacherRepository.findAll();
    }

    @Override
    public List<Teacher> getAllNewTeachersForStudent(Student student) {
        List<Teacher> newForStudentTeachers = jpaTeacherRepository.findAll();
        student = jpaStudentRepository.getOne(student.getId());
        Hibernate.initialize(student.getTeachers());
        newForStudentTeachers.removeAll(student.getTeachers());
        return newForStudentTeachers;
    }

    @Override
    public List<Student> getAllNotConfirmedStudent(Teacher teacher) {
        teacher = jpaTeacherRepository.getOne(teacher.getId());
        return teacher.getNotConfirmedStudents();
    }

    @Override
    @Transactional
    public void disagreeStudentById(long id, Teacher authenticatedTeacher) {
        Teacher teacher = jpaTeacherRepository.getOne(authenticatedTeacher.getId());
        Student student = jpaStudentRepository.getOne(id);
        teacher.getNotConfirmedStudents().remove(student);
        jpaTeacherRepository.save(teacher);
    }


    @Override
    @Transactional
    public void agreeStudentById(long id, Teacher authenticatedTeacher) {
        Teacher teacher = jpaTeacherRepository.getOne(authenticatedTeacher.getId());
        Student student = jpaStudentRepository.getOne(id);
        teacher.getNotConfirmedStudents().remove(student);
        teacher.getStudents().add(student);
        jpaTeacherRepository.save(teacher);
    }

    @Override
    public List<Student> getAllConfirmedStudent(Teacher authenticatedTeacher) {
        Teacher teacher = jpaTeacherRepository.getOne(authenticatedTeacher.getId());
        Hibernate.initialize(teacher.getStudents());
        return teacher.getStudents();
    }

    @Override
    public List<Teacher> getAllTeachersOfStudent(Student student) {
        student = jpaStudentRepository.getOne(student.getId());
        Hibernate.initialize(student.getTeachers());
        return student.getTeachers();
    }
}
