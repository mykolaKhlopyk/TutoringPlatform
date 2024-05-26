package com.mkh.tutoringplatform.service;

import com.mkh.tutoringplatform.domain.user.Teacher;

import java.util.List;

public interface TeacherService {

    Teacher findById(long id);

    List<Teacher> getAll();

    long getCourseOwnerId(long courseId);
}
