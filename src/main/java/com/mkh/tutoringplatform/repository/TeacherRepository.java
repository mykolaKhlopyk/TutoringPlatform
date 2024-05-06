package com.mkh.tutoringplatform.repository;

import com.mkh.tutoringplatform.domain.user.teacher.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository {

    Teacher getOne(long id);

    void save(Teacher teacher);

    Teacher findById(long id);

    List<Teacher> findAll();
}
