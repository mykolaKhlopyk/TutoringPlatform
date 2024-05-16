package com.mkh.tutoringplatform.domain.user;

import com.mkh.tutoringplatform.domain.user.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
