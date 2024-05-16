package com.mkh.tutoringplatform.domain.user;

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

    private String description;

    private Long teacherId;

    private String literature;

    private String tasks;

    private String links;

    @Override
    public String toString() {
        return "Course";
    }
}
