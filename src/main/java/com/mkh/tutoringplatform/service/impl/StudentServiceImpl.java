package com.mkh.tutoringplatform.service.impl;

import com.mkh.tutoringplatform.domain.user.Course;
import com.mkh.tutoringplatform.domain.user.Student;
import com.mkh.tutoringplatform.domain.user.Teacher;
import com.mkh.tutoringplatform.repository.jpa.JpaCourseRepository;
import com.mkh.tutoringplatform.repository.jpa.JpaStudentRepository;
import com.mkh.tutoringplatform.repository.jpa.JpaTeacherRepository;
import com.mkh.tutoringplatform.service.StudentService;
import lombok.AllArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class StudentServiceImpl implements StudentService {

    private final JpaTeacherRepository jpaTeacherRepository;

    private final JpaStudentRepository jpaStudentRepository;

    private final JpaCourseRepository jpaCourseRepository;

    @Override
    @Transactional
    public void requestTeacher(long id, Student authenticatedStudent) {
        Teacher teacher = jpaTeacherRepository.findById(id).get();
        authenticatedStudent = jpaStudentRepository.findById(authenticatedStudent.getId()).get();
        teacher.getNotConfirmedStudents().add(authenticatedStudent);
        jpaTeacherRepository.save(teacher);
    }

    @Override
    @Transactional
    public void cancelRequest(long id, Student authenticatedStudent) {
        Teacher teacher = jpaTeacherRepository.findById(id).get();
        authenticatedStudent = jpaStudentRepository.findById(authenticatedStudent.getId()).get();
        teacher.getNotConfirmedStudents().remove(authenticatedStudent);
        jpaTeacherRepository.save(teacher);
    }

    @Override
    public List<Teacher> getAllRequestedByStudent(Student student) {
        student = jpaStudentRepository.findById(student.getId()).get();
        Hibernate.initialize(student.getRequestedTeachers());
        return student.getRequestedTeachers();
    }

    @Override
    @Transactional
    public void deleteTeacher(long student_id, long teacher_id) {
        Teacher teacher = jpaTeacherRepository.getOne(teacher_id);
        Student student = jpaStudentRepository.getOne(student_id);
        student.getTeachers().remove(teacher);
        teacher.getStudents().remove(student);
        jpaStudentRepository.save(student);
        jpaTeacherRepository.save(teacher);
    }

    @Override
    public List<Course> getStudentCourses(long id) {
        return jpaStudentRepository.getOne(id).getCourses();
    }

    @Override
    @Transactional
    public void joinStudentToCourse(long studentId, long courseId) {
        Student student = jpaStudentRepository.getOne(studentId);
        Course course = jpaCourseRepository.getOne(courseId);
        course.getStudents().add(student);
        student.getCourses().add(course);
        jpaStudentRepository.save(student);
        jpaCourseRepository.save(course);
    }

    @Override
    @Transactional
    public void leaveCourse(long id, long courseId) {
        Student student = jpaStudentRepository.getOne(id);
        Course course = jpaCourseRepository.getOne(courseId);
        student.getCourses().remove(course);
        course.getStudents().remove(student);
        jpaStudentRepository.save(student);
        jpaCourseRepository.save(course);
    }
}
