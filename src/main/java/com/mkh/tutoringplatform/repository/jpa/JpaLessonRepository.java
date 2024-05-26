package com.mkh.tutoringplatform.repository.jpa;

import com.mkh.tutoringplatform.domain.user.Lesson;
import com.mkh.tutoringplatform.repository.entity.SqlLesson;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JpaLessonRepository extends JpaRepository<SqlLesson, Long> {

}
