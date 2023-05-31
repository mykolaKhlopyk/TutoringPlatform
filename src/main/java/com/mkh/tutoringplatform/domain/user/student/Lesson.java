package com.mkh.tutoringplatform.domain.user.student;

import com.mkh.tutoringplatform.domain.user.teacher.Subject;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Entity
@Data
@Table(name = "lessons")
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Subject subject;

    @NotNull
    //@Size(min = 10, max = 300, message = "durationq is incorrect")
    @Column(name = "duration_minutes")
    private int duration;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @Column(name = "time_start")
    private Date timeStart;
    @Transient
    private Date timeFinish;

    @ManyToOne
    @JoinColumn(name = "group_id", referencedColumnName = "id")
    private Group group;

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
}
