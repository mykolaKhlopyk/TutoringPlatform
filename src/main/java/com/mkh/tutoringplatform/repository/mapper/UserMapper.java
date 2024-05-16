package com.mkh.tutoringplatform.repository.mapper;

import com.mkh.tutoringplatform.domain.user.user.User;
import com.mkh.tutoringplatform.repository.entity.SqlUser;

public class UserMapper {

    public static User mapToDomainModel(SqlUser sqlUser) {
        boolean isStudent = sqlUser.getStudent() != null;
        return User.builder()
                .id(sqlUser.getId())
                .name(sqlUser.getName())
                .username(sqlUser.getUsername())
                .password(sqlUser.getPassword())
                .name(sqlUser.getName())
                .surname(sqlUser.getSurname())
                .phone(sqlUser.getPhone())
                .sex(sqlUser.getSex())
                .roles(sqlUser.getRoles())
                .userInitiatorId(isStudent ? sqlUser.getStudent().getId() : sqlUser.getTeacher().getId())
                .userRole(isStudent ? User.UserRole.STUDENT : User.UserRole.TEACHER)
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
                .userRole(user.getUserRole())
                .roles(user.getRoles())
                .build();
    }
}
