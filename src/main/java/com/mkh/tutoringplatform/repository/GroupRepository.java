package com.mkh.tutoringplatform.repository;

import com.mkh.tutoringplatform.domain.user.Group;

import java.util.List;
import java.util.Optional;

public interface GroupRepository {

    void save(Group group);

    Optional<Group> findById(long id);

    void deleteById(long id);

    List<Group> getGroupsFromCourse(long courseId);

    List<Group> getTeacherGroups(long teacherId);

    List<Group> getGroupsByIds(List<Long> groupsIds);
}
