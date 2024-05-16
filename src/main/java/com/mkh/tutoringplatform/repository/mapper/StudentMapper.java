package com.mkh.tutoringplatform.repository.mapper;

import com.mkh.tutoringplatform.domain.user.Student;
import com.mkh.tutoringplatform.repository.entity.SqlCourse;
import com.mkh.tutoringplatform.repository.entity.SqlGroup;
import com.mkh.tutoringplatform.repository.entity.SqlStudent;

import java.util.stream.Stream;

public class StudentMapper {

    public static Student mapToDomainModel(SqlStudent sqlStudent) {
        var coursesIds = Stream.concat(
                sqlStudent.getCourses().stream(),
                sqlStudent.getRequestedCourses().stream()
        ).map(SqlCourse::getId).toList();

        return Student.builder()
                .id(sqlStudent.getId())
                .user(UserMapper.mapToDomainModel(sqlStudent.getUser()))
                .coursesIds(coursesIds)
                .groupsIds(sqlStudent.getGroups().stream().map(SqlGroup::getId).toList())
                .build();
    }

    public static SqlStudent mapToSqlModelWithoutDependencies(Student student) {
        return SqlStudent.builder()
                .id(student.getId())
                .user(UserMapper.mapToSqlModelWithoutDependencies(student.getUser()))
                .build();
    }
}
