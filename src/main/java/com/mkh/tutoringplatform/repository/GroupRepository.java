package com.mkh.tutoringplatform.repository;

import com.mkh.tutoringplatform.domain.user.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GroupRepository {

    void save(Group group);

    Optional<Group> findById(long id);

    void deleteById(long id);
}
