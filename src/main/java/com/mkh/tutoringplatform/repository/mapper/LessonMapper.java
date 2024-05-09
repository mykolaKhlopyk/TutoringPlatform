package com.mkh.tutoringplatform.repository.mapper;

import com.mkh.tutoringplatform.domain.user.Lesson;
import com.mkh.tutoringplatform.repository.entity.SqlLesson;

public class LessonMapper {

    public static Lesson mapToDomainModel(SqlLesson sqlLesson) {
        return Lesson.builder()
                .id(sqlLesson.getId())
                .name(sqlLesson.getName())
                .duration(sqlLesson.getDuration())
                .groupId(sqlLesson.getSqlGroup().getId())
                .timeStart(sqlLesson.getTimeStart())
                .build();
    }

    public static SqlLesson mapToSqlModelWithoutDependencies(Lesson lesson) {
        return SqlLesson.builder()
                .id(lesson.getId())
                .name(lesson.getName())
                .duration(lesson.getDuration())
                .timeStart(lesson.getTimeStart())
                .build();
    }
}
