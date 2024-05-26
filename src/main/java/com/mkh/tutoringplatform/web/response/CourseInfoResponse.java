package com.mkh.tutoringplatform.web.response;

import lombok.Builder;

@Builder
public record CourseInfoResponse(
        String name,
        String description,
        String teacherName,
        long courseId,
        String literature,
        String tasks,
        String links,
        boolean showForOwner
) {
}
