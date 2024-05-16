package com.mkh.tutoringplatform.domain.user;

import com.mkh.tutoringplatform.domain.user.user.User;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {

    private long id;

    private User user;

    @NotNull
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date registeredAt;

    private List<Long> groupsIds;

    private List<Long> coursesIds;

    @Override
    public String toString() {
        return "Teacher";
    }
}
