package com.mkh.tutoringplatform.repository;

import com.mkh.tutoringplatform.domain.user.Lesson;

import java.util.Optional;

public interface LessonRepository {

    void deleteById(long id);

    Optional<Lesson> findById(long id);

    void save(Lesson lesson);
}
