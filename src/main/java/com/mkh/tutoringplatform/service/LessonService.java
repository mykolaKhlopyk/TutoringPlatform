package com.mkh.tutoringplatform.service;

import com.mkh.tutoringplatform.domain.user.Lesson;
import com.mkh.tutoringplatform.domain.user.Student;
import com.mkh.tutoringplatform.domain.user.Teacher;

import java.util.Date;
import java.util.List;

public interface LessonService {

    void createLesson(long groupId, String lessonName, Date timeStart, int duration);

    List<Lesson> getTeacherLessons(long teacherId);

    List<Lesson> getTodayLessonForStudent(Student student);

    List<Lesson> getTodayLessonForTeacher(Teacher teacher);

    List<Lesson> getLessonsFromGroup(long groupId);

    void deleteLesson(long lessonId);

}
