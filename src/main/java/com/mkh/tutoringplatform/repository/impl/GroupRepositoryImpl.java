package com.mkh.tutoringplatform.repository.impl;

import com.mkh.tutoringplatform.domain.user.Group;
import com.mkh.tutoringplatform.repository.GroupRepository;
import com.mkh.tutoringplatform.repository.entity.SqlGroup;
import com.mkh.tutoringplatform.repository.jpa.JpaCourseRepository;
import com.mkh.tutoringplatform.repository.jpa.JpaGroupRepository;
import com.mkh.tutoringplatform.repository.jpa.JpaStudentRepository;
import com.mkh.tutoringplatform.repository.jpa.JpaTeacherRepository;
import com.mkh.tutoringplatform.repository.mapper.GroupMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class GroupRepositoryImpl implements GroupRepository {

    private final JpaGroupRepository jpaGroupRepository;

    private final JpaTeacherRepository jpaTeacherRepository;

    private final JpaStudentRepository jpaStudentRepository;

    private final JpaCourseRepository jpaCourseRepository;

    @Override
    public void save(Group group) {
        SqlGroup sqlGroup = GroupMapper.mapToSqlModelWithoutDependencies(group);
        sqlGroup.setStudents(group.getStudentsIds().stream().map(jpaStudentRepository::getReferenceById).toList());
        sqlGroup.setCourse(jpaCourseRepository.getReferenceById(group.getCourseId()));
        jpaGroupRepository.save(sqlGroup);
    }

    @Override
    public Optional<Group> findById(long id) {
        return jpaGroupRepository.findById(id).map(GroupMapper::mapToDomainModel);
    }

    @Override
    public void deleteById(long id) {
        jpaGroupRepository.deleteById(id);
    }

    @Override
    public List<Group> getGroupsFromCourse(long courseId) {
        return jpaCourseRepository.getReferenceById(courseId)
                .getGroups().stream()
                .map(GroupMapper::mapToDomainModel)
                .toList();
    }
}
