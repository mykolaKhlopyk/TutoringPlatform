package com.mkh.tutoringplatform.repository;

import com.mkh.tutoringplatform.domain.user.student.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface GroupRepository {

    void save(Group group);

    Group getOne(long groupId);

    void deleteById(long groupId);
}
