package com.mkh.tutoringplatform.service;

import com.mkh.tutoringplatform.domain.user.student.Lesson;
import com.mkh.tutoringplatform.domain.user.student.Student;
import com.mkh.tutoringplatform.domain.user.teacher.Teacher;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    void requestTeacher(long id, Student authenticatedStudent);
    List<Teacher> getAllRequestedByStudent(Student student);

    void cancelRequest(long id, Student authenticatedStudent);

    void deleteTeacher(long student_id, long teacher_id);
}
