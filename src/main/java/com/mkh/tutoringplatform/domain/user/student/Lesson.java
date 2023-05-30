package com.mkh.tutoringplatform.domain.user.student;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "lessons")
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Duration duration;

    @NotNull
    @Column(name = "time_start")
    private LocalDateTime timeStart;

    @ManyToOne
    @JoinColumn(name = "group_id", referencedColumnName = "id")
    private Group group;
}
