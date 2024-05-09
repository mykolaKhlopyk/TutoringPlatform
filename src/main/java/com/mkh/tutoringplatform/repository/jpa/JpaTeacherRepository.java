package com.mkh.tutoringplatform.repository.jpa;

import com.mkh.tutoringplatform.domain.user.Teacher;
import com.mkh.tutoringplatform.repository.entity.SqlTeacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaTeacherRepository extends JpaRepository<SqlTeacher, Long> {

}
