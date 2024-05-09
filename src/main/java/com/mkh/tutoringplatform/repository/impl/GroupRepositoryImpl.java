package com.mkh.tutoringplatform.repository.impl;

import com.mkh.tutoringplatform.domain.user.Group;
import com.mkh.tutoringplatform.repository.GroupRepository;
import com.mkh.tutoringplatform.repository.jpa.JpaGroupRepository;
import com.mkh.tutoringplatform.repository.mapper.GroupMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class GroupRepositoryImpl implements GroupRepository {

    private final JpaGroupRepository jpaGroupRepository;

    public GroupRepositoryImpl(JpaGroupRepository jpaGroupRepository) {
        this.jpaGroupRepository = jpaGroupRepository;
    }

    @Override
    public void save(Group group) {
        jpaGroupRepository.save(GroupMapper.mapToSqlModelWithoutDependencies(group));
    }

    @Override
    public Optional<Group> findById(long id) {
        return jpaGroupRepository.findById(id).map(GroupMapper::mapToDomainModel);
    }

    @Override
    public void deleteById(long id) {
        jpaGroupRepository.deleteById(id);
    }
}
