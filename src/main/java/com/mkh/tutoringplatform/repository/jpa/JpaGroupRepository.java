package com.mkh.tutoringplatform.repository.jpa;

import com.mkh.tutoringplatform.repository.entity.SqlGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaGroupRepository extends JpaRepository<SqlGroup, Long>{

}
