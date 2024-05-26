package com.mkh.tutoringplatform.web.response;

import com.mkh.tutoringplatform.domain.user.Group;
import com.mkh.tutoringplatform.domain.user.Lesson;
import lombok.Builder;

import java.time.Instant;
import java.time.temporal.TemporalAmount;

@Builder
public record LessonInfoResponse(
        long id,
        String name,
        String description,
        String timeStart,
        String timeFinish,
        long groupId,
        String groupName
) {
    public static LessonInfoResponse of(Lesson lesson, Group group) {
        return new LessonInfoResponse(
                lesson.getId(),
                lesson.getName(),
                lesson.getDescription(),
                lesson.getTimeStart().toString(),
                lesson.getTimeStart().toInstant().plusSeconds(lesson.getDuration() * 60L).toString(),
                group.getId(),
                group.getName()
        );
    }
}
