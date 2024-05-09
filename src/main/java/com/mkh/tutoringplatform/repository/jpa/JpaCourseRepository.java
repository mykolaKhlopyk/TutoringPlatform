package com.mkh.tutoringplatform.repository.jpa;

import com.mkh.tutoringplatform.repository.entity.SqlCourse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.stream.Stream;

public interface JpaCourseRepository extends JpaRepository<SqlCourse, Long> {

    boolean existsCourseByName(String name);
}
