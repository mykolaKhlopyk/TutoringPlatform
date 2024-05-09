package com.mkh.tutoringplatform.domain.user;

import com.mkh.tutoringplatform.repository.entity.SqlStudent;
import com.mkh.tutoringplatform.repository.entity.SqlTeacher;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Size;
import java.util.List;

@Data
@Builder
public class Course {

    private long id;

    @Size(min = 3, max = 16, message = "Course name should be between 3 and 16 characters")
    private String name;

    private List<Long> studentsIds;

    private Long teacherId;

    private String literature;

    private String tasks;

    private String links;

    @Override
    public String toString() {
        return "Course";
    }
}
