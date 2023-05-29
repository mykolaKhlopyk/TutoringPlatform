package com.mkh.tutoringplatform.domain.user.student;

import com.mkh.tutoringplatform.domain.user.teacher.Teacher;
import com.mkh.tutoringplatform.domain.user.user.User;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @NotNull
    @Enumerated(EnumType.ORDINAL)
    private Grade grade;

    @ManyToMany(mappedBy = "students")
    private List<Teacher> teachers;

    @ManyToMany(mappedBy = "notConfirmedStudents")
    private List<Teacher> requestedTeachers;

    @ManyToMany(mappedBy = "students")
    private List<Group> groups;
}
