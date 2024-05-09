package com.mkh.tutoringplatform.repository.impl;

import com.mkh.tutoringplatform.domain.user.Student;
import com.mkh.tutoringplatform.repository.StudentRepository;
import com.mkh.tutoringplatform.repository.jpa.JpaStudentRepository;
import com.mkh.tutoringplatform.repository.mapper.StudentMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class StudentRepositoryImpl implements StudentRepository {

    private final JpaStudentRepository jpaStudentRepository;

    public StudentRepositoryImpl(JpaStudentRepository jpaStudentRepository) {
        this.jpaStudentRepository = jpaStudentRepository;
    }

    @Override
    public Optional<Student> findById(long id) {
        return jpaStudentRepository.findById(id).map(StudentMapper::mapToDomainModel);
    }

    @Override
    public void save(Student student) {
        jpaStudentRepository.save(StudentMapper.mapToSqlModelWithoutDependencies(student));
    }
}