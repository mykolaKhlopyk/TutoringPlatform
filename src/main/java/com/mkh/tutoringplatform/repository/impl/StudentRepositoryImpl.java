package com.mkh.tutoringplatform.repository.impl;

import com.mkh.tutoringplatform.domain.user.Student;
import com.mkh.tutoringplatform.repository.StudentRepository;
import com.mkh.tutoringplatform.repository.jpa.JpaCourseRepository;
import com.mkh.tutoringplatform.repository.jpa.JpaStudentRepository;
import com.mkh.tutoringplatform.repository.mapper.StudentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class StudentRepositoryImpl implements StudentRepository {

    private final JpaStudentRepository jpaStudentRepository;

    private final JpaCourseRepository jpaCourseRepository;

    @Override
    public Optional<Student> findById(long id) {
        return jpaStudentRepository.findById(id).map(StudentMapper::mapToDomainModel);
    }

    @Override
    public long save(Student student) {
        return jpaStudentRepository.save(StudentMapper.mapToSqlModelWithoutDependencies(student)).getId();
    }

    @Override
    public void joinStudentToCourse(long studentId, long courseId) {
        var student = jpaStudentRepository.getReferenceById(studentId);
        var course = jpaCourseRepository.getReferenceById(courseId);
        student.getCourses().add(course);
    }

    @Override
    public void leaveStudentFromCourse(long studentId, long courseId) {
        var student = jpaStudentRepository.getReferenceById(studentId);
//        var course = jpaCourseRepository.getReferenceById(courseId);
        student.getCourses().removeIf(studentCourse -> studentCourse.getId() == courseId);
    }

    @Override
    public List<Student> getAllStudentsAskedForSubscribeForCourse(long courseId) {
        var course = jpaCourseRepository.getReferenceById(courseId);
        return course.getStudentsWithRequest().stream().map(StudentMapper::mapToDomainModel).toList();
    }

    @Override
    public List<Student> getAllStudentsFromCourse(long courseId) {
        var course = jpaCourseRepository.getReferenceById(courseId);
        return course.getStudents().stream().map(StudentMapper::mapToDomainModel).toList();
    }
}
