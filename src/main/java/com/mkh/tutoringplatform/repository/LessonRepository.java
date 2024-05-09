package com.mkh.tutoringplatform.repository;

import com.mkh.tutoringplatform.domain.user.Lesson;

import java.util.List;
import java.util.Optional;

public interface LessonRepository {

    void deleteById(long id);

    Optional<Lesson> findById(long id);

    void save(Lesson lesson);

    List<Lesson> getTeacherLessons(long teacherId);

    List<Lesson> getLessonsFromGroups(List<Long> groupsIds);
}
