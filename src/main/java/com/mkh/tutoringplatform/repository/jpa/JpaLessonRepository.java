package com.mkh.tutoringplatform.repository.jpa;

import com.mkh.tutoringplatform.domain.user.Lesson;
import com.mkh.tutoringplatform.repository.entity.SqlLesson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaLessonRepository extends JpaRepository<SqlLesson, Long> {

}
