package com.mkh.tutoringplatform.repository.mapper;

import com.mkh.tutoringplatform.domain.user.Group;
import com.mkh.tutoringplatform.repository.entity.SqlGroup;
import com.mkh.tutoringplatform.repository.entity.SqlLesson;
import com.mkh.tutoringplatform.repository.entity.SqlStudent;

public class GroupMapper {

    public static Group mapToDomainModel(SqlGroup sqlGroup) {
        return Group.builder()
                .id(sqlGroup.getId())
                .name(sqlGroup.getName())
                .courseId(sqlGroup.getCourse().getId())
                .studentsIds(sqlGroup.getStudents().stream().map(SqlStudent::getId).toList())
                .lessonsIds(sqlGroup.getLessons().stream().map(SqlLesson::getId).toList())
                .build();
    }

    public static SqlGroup mapToSqlModelWithoutDependencies(Group group) {
        return SqlGroup.builder()
                .id(group.getId())
                .name(group.getName())
                .build();
    }
}
