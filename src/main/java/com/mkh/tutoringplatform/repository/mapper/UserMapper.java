package com.mkh.tutoringplatform.repository.mapper;

import com.mkh.tutoringplatform.domain.user.user.Sex;
import com.mkh.tutoringplatform.domain.user.user.User;
import com.mkh.tutoringplatform.repository.entity.SqlUser;
import org.springframework.stereotype.Component;

public class UserMapper {

    public static User mapToDomainModel(SqlUser sqlUser) {
        return User.builder()
                .id(sqlUser.getId())
                .name(sqlUser.getName())
                .username(sqlUser.getUsername())
                .password(sqlUser.getPassword())
                .name(sqlUser.getName())
                .surname(sqlUser.getSurname())
                .phone(sqlUser.getPhone())
                .sex(sqlUser.getSex())
                .userInitiatorId(sqlUser.getStudent() != null ? sqlUser.getStudent().getId() : sqlUser.getTeacher().getId())
                .userRole(sqlUser.getStudent() != null ? User.UserRole.STUDENT : User.UserRole.TEACHER)
                .build();
    }

    public static SqlUser mapToSqlModelWithoutDependencies(User user) {
        return SqlUser.builder()
                .id(user.getId())
                .email(user.getEmail())
                .username(user.getUsername())
                .password(user.getPassword())
                .name(user.getName())
                .surname(user.getSurname())
                .phone(user.getPhone())
                .sex(user.getSex())
                .roles(user.getRoles())
                .build();
    }
}
