package com.mkh.tutoringplatform.domain.user.student;

import com.mkh.tutoringplatform.domain.user.user.User;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @NotNull
    @Enumerated(EnumType.ORDINAL)
    private Grade grade;

}
