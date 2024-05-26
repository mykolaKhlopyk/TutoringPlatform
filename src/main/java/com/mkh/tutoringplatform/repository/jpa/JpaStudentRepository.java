package com.mkh.tutoringplatform.repository.jpa;

import com.mkh.tutoringplatform.domain.user.Student;
import com.mkh.tutoringplatform.repository.entity.SqlStudent;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaStudentRepository extends JpaRepository<SqlStudent, Long> {

}
