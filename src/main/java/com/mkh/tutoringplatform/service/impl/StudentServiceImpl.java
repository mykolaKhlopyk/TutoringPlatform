package com.mkh.tutoringplatform.service.impl;

import com.mkh.tutoringplatform.domain.user.student.Course;
import com.mkh.tutoringplatform.domain.user.student.Student;
import com.mkh.tutoringplatform.domain.user.teacher.Teacher;
import com.mkh.tutoringplatform.repository.CourseRepository;
import com.mkh.tutoringplatform.repository.StudentRepository;
import com.mkh.tutoringplatform.repository.TeacherRepository;
import com.mkh.tutoringplatform.service.StudentService;
import lombok.AllArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class StudentServiceImpl implements StudentService {

    private final TeacherRepository teacherRepository;

    private final StudentRepository studentRepository;

    private final CourseRepository courseRepository;

    @Override
    @Transactional
    public void requestTeacher(long id, Student authenticatedStudent) {
        Teacher teacher = teacherRepository.findById(id).get();
        authenticatedStudent = studentRepository.findById(authenticatedStudent.getId()).get();
        teacher.getNotConfirmedStudents().add(authenticatedStudent);
        teacherRepository.save(teacher);
    }

    @Override
    @Transactional
    public void cancelRequest(long id, Student authenticatedStudent) {
        Teacher teacher = teacherRepository.findById(id).get();
        authenticatedStudent = studentRepository.findById(authenticatedStudent.getId()).get();
        teacher.getNotConfirmedStudents().remove(authenticatedStudent);
        teacherRepository.save(teacher);
    }

    @Override
    public List<Teacher> getAllRequestedByStudent(Student student) {
        student = studentRepository.findById(student.getId()).get();
        Hibernate.initialize(student.getRequestedTeachers());
        return student.getRequestedTeachers();
    }

    @Override
    @Transactional
    public void deleteTeacher(long student_id, long teacher_id) {
        Teacher teacher = teacherRepository.getOne(teacher_id);
        Student student = studentRepository.getOne(student_id);
        student.getTeachers().remove(teacher);
        teacher.getStudents().remove(student);
        studentRepository.save(student);
        teacherRepository.save(teacher);
    }

    @Override
    public List<Course> getStudentCourses(long id) {
        return studentRepository.getOne(id).getCourses();
    }

    @Override
    @Transactional
    public void joinStudentToCourse(long studentId, long courseId) {
        Student student = studentRepository.getOne(studentId);
        Course course = courseRepository.getOne(courseId);
        course.getStudents().add(student);
        student.getCourses().add(course);
        studentRepository.save(student);
        courseRepository.save(course);
    }

    @Override
    @Transactional
    public void leaveCourse(long id, long courseId) {
        Student student = studentRepository.getOne(id);
        Course course = courseRepository.getOne(courseId);
        student.getCourses().remove(course);
        course.getStudents().remove(student);
        studentRepository.save(student);
        courseRepository.save(course);
    }

}
