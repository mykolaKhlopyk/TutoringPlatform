package com.mkh.tutoringplatform.repository.impl;

import com.mkh.tutoringplatform.domain.user.student.Group;
import com.mkh.tutoringplatform.repository.GroupRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class GroupRepositoryImpl implements GroupRepository {

    private final JdbcTemplate jdbcTemplate;

    public GroupRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(Group group) {

    }

    @Override
    public Group getOne(long groupId) {
        return null;
    }

    @Override
    public void deleteById(long groupId) {
        // TODO document why this method is empty
    }
}
