package com.mkh.tutoringplatform.repository.jpa;

import com.mkh.tutoringplatform.repository.entity.SqlCourse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCourseRepository extends JpaRepository<SqlCourse, Long> {

    boolean existsCourseByName(String name);
}
