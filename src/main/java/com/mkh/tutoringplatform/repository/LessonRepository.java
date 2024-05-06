package com.mkh.tutoringplatform.repository;

import com.mkh.tutoringplatform.domain.user.student.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonRepository {
    void delete(Lesson lesson);

    Lesson getOne(long lessonId);

    void save(Lesson lesson);
}
