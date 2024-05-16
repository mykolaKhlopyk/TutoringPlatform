package com.mkh.tutoringplatform.service.impl;

import com.mkh.tutoringplatform.domain.exception.ResourceNotFoundException;
import com.mkh.tutoringplatform.domain.user.Student;
import com.mkh.tutoringplatform.repository.StudentRepository;
import com.mkh.tutoringplatform.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    public Student findById(long studentId) {
        return studentRepository.findById(studentId).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public void joinStudentToCourse(long studentId, long courseId) {
        studentRepository.joinStudentToCourse(studentId, courseId);
    }

    @Override
    public void leaveStudentFromCourse(long studentId, long courseId) {
        studentRepository.leaveStudentFromCourse(studentId, courseId);
    }

    @Override
    public List<Student> getAllStudentsAskedForSubscribeForCourse(long courseId) {
        return studentRepository.getAllStudentsAskedForSubscribeForCourse(courseId);
    }

    @Override
    public List<Student> getAllStudentsFromCourse(long courseId) {
        return studentRepository.getAllStudentsFromCourse(courseId);
    }
}
