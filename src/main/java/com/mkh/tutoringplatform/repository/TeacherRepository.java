package com.mkh.tutoringplatform.repository;

import com.mkh.tutoringplatform.domain.user.teacher.Teacher;
import com.mkh.tutoringplatform.domain.user.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

}
