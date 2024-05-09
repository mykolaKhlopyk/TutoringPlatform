package com.mkh.tutoringplatform.repository;

import com.mkh.tutoringplatform.domain.user.Group;

import java.util.List;
import java.util.Optional;

public interface GroupRepository {

    void save(Group group);

    Optional<Group> findById(long id);

    void deleteById(long id);

    List<Group> getGroups(List<Long> groupsIds);
}
