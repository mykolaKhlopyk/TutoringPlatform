package com.mkh.tutoringplatform.domain.user;

import com.mkh.tutoringplatform.domain.user.user.User;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Student {

    private long id;

    private User user;

    private List<Long> coursesIds;

    private List<Long> groupsIds;

    @Override
    public String toString() {
        return "Student";
    }
}
