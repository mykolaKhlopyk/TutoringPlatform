package com.mkh.tutoringplatform.repository;

import com.mkh.tutoringplatform.domain.user.teacher.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

}
