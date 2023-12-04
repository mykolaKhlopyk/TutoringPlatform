package com.mkh.tutoringplatform.service;

import com.mkh.tutoringplatform.domain.user.student.Student;
import com.mkh.tutoringplatform.domain.user.teacher.Teacher;

import java.util.List;

public interface TeacherService {

    Teacher getById(long id);

    List<Teacher> getAll();

    List<Teacher> getAllNewTeachersForStudent(Student student);

    List<Teacher> getAllTeachersOfStudent(Student student);

    List<Student> getAllNotConfirmedStudent(Teacher teacher);

    void disagreeStudentById(long id, Teacher authenticatedTeacher);

    void agreeStudentById(long id, Teacher authenticatedTeacher);

    List<Student> getAllConfirmedStudent(Teacher authenticatedTeacher);
}
