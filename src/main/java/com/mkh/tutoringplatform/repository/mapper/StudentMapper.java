package com.mkh.tutoringplatform.repository.mapper;

import com.mkh.tutoringplatform.domain.user.Lesson;
import com.mkh.tutoringplatform.domain.user.Student;
import com.mkh.tutoringplatform.repository.entity.SqlCourse;
import com.mkh.tutoringplatform.repository.entity.SqlGroup;
import com.mkh.tutoringplatform.repository.entity.SqlLesson;
import com.mkh.tutoringplatform.repository.entity.SqlStudent;
import org.springframework.stereotype.Component;

public class StudentMapper {

    public static Student mapToDomainModel(SqlStudent sqlStudent) {
        return Student.builder()
                .id(sqlStudent.getId())
                .user(UserMapper.mapToDomainModel(sqlStudent.getUser()))
                .coursesIds(sqlStudent.getCourses().stream().map(SqlCourse::getId).toList())
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
