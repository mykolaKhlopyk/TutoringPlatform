package com.mkh.tutoringplatform.repository.mapper;

import com.mkh.tutoringplatform.domain.user.Teacher;
import com.mkh.tutoringplatform.repository.entity.SqlCourse;
import com.mkh.tutoringplatform.repository.entity.SqlGroup;
import com.mkh.tutoringplatform.repository.entity.SqlTeacher;

public class TeacherMapper {

    public static Teacher mapToDomainModel(SqlTeacher sqlTeacher) {
        return Teacher.builder()
                .id(sqlTeacher.getId())
                .user(UserMapper.mapToDomainModel(sqlTeacher.getUser()))
                .registeredAt(sqlTeacher.getRegisteredAt())
                .groupsIds(sqlTeacher.getSqlGroups().stream().map(SqlGroup::getId).toList())
                .coursesIds(sqlTeacher.getCourses().stream().map(SqlCourse::getId).toList())
                .build();
    }

    public static SqlTeacher mapToSqlModelWithoutDependencies(Teacher teacher) {
        return SqlTeacher.builder()
                .id(teacher.getId())
                .user(UserMapper.mapToSqlModelWithoutDependencies(teacher.getUser()))
                .registeredAt(teacher.getRegisteredAt())
                .build();
    }
}
