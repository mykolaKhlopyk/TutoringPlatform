package com.mkh.tutoringplatform.domain.user;

import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Data
@Builder
public class Lesson {
    
    private long id;

    @NotNull
    @Size(min=4, max = 16, message = "Lesson name should be between 6 and 16 characters")
    private String name;

    @NotNull
    @Size(min = 1, max = 300, message = "duration is incorrect")
    private int duration;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date timeStart;

    private long groupId;

    public Date getTimeFinish() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(getTimeStart());
        calendar.add(Calendar.MINUTE, getDuration());
        return calendar.getTime();
    }

    public String getStringTimeFinish() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(getTimeFinish());
    }

    @Override
    public String toString() {
        return "Lesson";
    }
}
