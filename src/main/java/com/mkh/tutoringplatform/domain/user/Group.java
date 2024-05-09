package com.mkh.tutoringplatform.domain.user;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Size;
import java.util.List;

@Data
@Builder
public class Group {

    private long id;

    @NotNull
    @Size(min=6, max = 16, message = "Group should be between 6 and 16 characters")
    private String name;

    private List<Long> studentsIds;

    private List<Long> lessonsIds;

    @Override
    public String toString() {
        return "Group";
    }
}
