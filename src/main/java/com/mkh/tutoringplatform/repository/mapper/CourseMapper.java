package com.mkh.tutoringplatform.repository.mapper;

import com.mkh.tutoringplatform.domain.user.Course;
import com.mkh.tutoringplatform.repository.entity.SqlCourse;
import com.mkh.tutoringplatform.repository.entity.SqlStudent;

public class CourseMapper {

    public static Course mapToDomainModel(SqlCourse sqlCourse) {
        return Course.builder()
                .id(sqlCourse.getId())
                .name(sqlCourse.getName())
                .literature(sqlCourse.getLiterature())
                .tasks(sqlCourse.getTasks())
                .links(sqlCourse.getLinks())
                .studentsIds(sqlCourse.getStudents().stream().map(SqlStudent::getId).toList())
                .teacherId(sqlCourse.getTeacher().getId())
                .build();
    }

    public static SqlCourse mapToSqlModelWithoutDependencies(Course course) {
        return SqlCourse.builder()
                .id(course.getId())
                .name(course.getName())
                .links(course.getLinks())
                .literature(course.getLiterature())
                .tasks(course.getTasks())
                .build();
    }
}
