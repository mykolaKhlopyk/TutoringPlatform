package com.mkh.tutoringplatform.service.impl;

import com.mkh.tutoringplatform.domain.exception.ResourceNotFoundException;
import com.mkh.tutoringplatform.domain.user.Teacher;
import com.mkh.tutoringplatform.repository.TeacherRepository;
import com.mkh.tutoringplatform.service.TeacherService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;

    @Override
    public Teacher findById(long id) {
        return teacherRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public List<Teacher> getAll() {
        return teacherRepository.findAll();
    }

    @Override
    public long getCourseOwnerId(long courseId) {
        return teacherRepository.getCourseOwnerId(courseId);
    }
}
