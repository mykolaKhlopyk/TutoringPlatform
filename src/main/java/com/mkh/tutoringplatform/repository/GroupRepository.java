package com.mkh.tutoringplatform.repository;

import com.mkh.tutoringplatform.domain.user.student.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Long>{

}
