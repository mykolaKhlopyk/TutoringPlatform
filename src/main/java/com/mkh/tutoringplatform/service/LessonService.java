package com.mkh.tutoringplatform.service;

import com.mkh.tutoringplatform.domain.user.Lesson;
import com.mkh.tutoringplatform.domain.user.Student;
import com.mkh.tutoringplatform.domain.user.Teacher;

import java.util.List;

public interface LessonService {

    void save(Lesson lesson, long group_id);

    List<Lesson> getLessonByTeacherId(long teacher_id, long student_id);

    List<Lesson> getLessonInAboutOneDay(Student student);

    List<Lesson> getLessonInAboutOneDay(Teacher teacher);
}
